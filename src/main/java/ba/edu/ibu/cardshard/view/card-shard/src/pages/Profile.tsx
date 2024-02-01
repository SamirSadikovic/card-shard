import useCurrentUser from "../hooks/useCurrentUser";
import Highcharts from 'highcharts'
import HighchartsReact from 'highcharts-react-official'
import useCollections from "../hooks/useCollections";
import useCardsByIds from "../hooks/useCardsByIds";
import { CollectedCard, Deck } from "../utils/types";
import useDecksByUser from "../hooks/useDecksByUser";
import useTags from "../hooks/useTags";
import { defaultPreview } from "../constants";

type GraphSeries = {
  name: string,
  data: number[]
}

const Profile = () => {
  const { data: user } = useCurrentUser();
  const { data: collection, isLoading: isCollectionLoading, isError: isCollectionError } = useCollections(user?.id!);
  const { data: decks, isLoading: isDecksLoading, isError: isDecksError } = useDecksByUser(user?.id!);
  const { data: tags, isLoading: isTagsLoading, isError: isTagsError } = useTags(collection?.id!);

  const _getAllCardIds = () => {
    var ids: number[] = [];

    collection?.cards.map(c => ids.push(c.id.cardId))
    decks?.map(deck => {
      deck?.main.map(cardId => ids.push(cardId))
      deck?.side.map(cardId => ids.push(cardId))
      deck?.extra.map(cardId => ids.push(cardId))
    })

    return ids.length != 0? ids : [defaultPreview.id];
  }

  const { data: cardInfo, isLoading: isCardInfoLoading, isError: isCardInfoError } = useCardsByIds(_getAllCardIds());

  const _getCollectionSize = () => {
    var size = 0;
    collection?.cards.map(card => size += card.quantity);
    return size;
  }

  const _getCardInfoById = (cardId: number) => {
    return cardInfo?.find(c => c.id === cardId);
  }

  const _getType = (card: CollectedCard) => {
    return cardInfo?.find(c => c.id == card.id.cardId)?.type;
  }

  const _getPrice = (displayCard: CollectedCard) => {
    const price = cardInfo?.find(c => c.id == displayCard.id.cardId)!.cardSets.find(s => s.setCode === displayCard.id.setCode)?.setPrice;
    return (price === "0" || price === "0.0" || price === "0.00")? 0 : Number(price);
  }

  const _roundPrice = (price: number) => {
    return Math.round((price + Number.EPSILON) * 100) / 100
  }

  const _getPricePerType = (type: string) => {
    var price = 0;

    collection?.cards.map(card => (
      price += _getType(card)?.includes(type)? (_getPrice(card) * card.quantity) : 0
    ))
    
    return price;
  }

  const _getCollectionValue = () => {
    var value = 0;

    collection?.cards.map(card => (
      value +=  (_getPrice(card) * card.quantity)
    ))
    
    return value;
  }

  const _getDeckCountPerType = (deck: Deck, type: string) => {
    var count = 0;

    deck?.main.map(cardId => (
      count += _getCardInfoById(cardId)?.type.includes(type)? 1 : 0
    ))

    deck?.side.map(cardId => (
      count += _getCardInfoById(cardId)?.type.includes(type)? 1 : 0
    ))

    deck?.extra.map(cardId => (
      count += _getCardInfoById(cardId)?.type.includes(type)? 1 : 0
    ))
    
    return count;
  }

  const _getDeckSeries = () => {
    var deckSeries: GraphSeries[] = [];

    decks?.map((deck) => (
      deckSeries.push({
        name: deck.name,
        data: [_getDeckCountPerType(deck, "Monster"), _getDeckCountPerType(deck, "Spell"), _getDeckCountPerType(deck, "Trap")]
      })
    ));
    
    return deckSeries;
  }
  
  const priceGraphOptions = {
    chart: {
      type: 'column'
    },
    title: {
      text: 'Collection Total Prices by Card Types',
      align: 'left'
    },
    xAxis: {
      categories: ['Monster', 'Spell', 'Trap'],
      crosshair: true,
      accessibility: {
        description: 'Card Types'
      }
    },
    yAxis: {
      min: 0,
      title: {
          text: 'Total Price (USD)'
      }
    },
    tooltip: {
      valuePrefix: '$'
    },
    plotOptions: {
      column: {
          pointPadding: 0.2,
          borderWidth: 0
      }
    },
    series: [
      {
        name: 'Price',
        data: [_roundPrice(_getPricePerType("Monster")), _roundPrice(_getPricePerType("Spell")), _roundPrice(_getPricePerType("Trap"))]
      }
    ]
  }

  const deckPercentageOptions = {
    chart: {
      type: 'bar'
    },
    title: {
      text: 'Card Type Usage in Decks',
      align: 'left'
    },
    xAxis: {
      categories: ['Monster', 'Spell', 'Trap'],
      crosshair: true,
      accessibility: {
        description: 'Card Types'
      }
    },
    yAxis: {
      min: 0,
      title: {
        text: 'Number of Cards'
      }
    },
    tooltip: {
      valueSuffix: ' cards'
    },
    plotOptions: {
      column: {
        pointPadding: 0.1,
        borderWidth: 0
      }
    },
    series: _getDeckSeries()
  }

  return (
    <div className="container-lg mt-5">
      <h3 className="text-center">Profile</h3>
      <hr/>
      {
        (isCollectionLoading || isCardInfoLoading || isDecksLoading || isTagsLoading) &&
        <div className="text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      }
      {
        (isCollectionError || isCardInfoError || isDecksError || isTagsError) &&
        <div className="alert alert-danger" role="alert">
            <p className="mb-0">
                Something went wrong, please try again.
            </p>
        </div>
      }
      {
        !isCollectionLoading && !isCardInfoLoading && !isDecksLoading && cardInfo && collection && decks &&
        <div className="main-body">
          <div className="row gutters-sm">
            <div className="col-md-4 mb-3">
              <div className="profile-card card">
                <div className="card-body">
                  <div className="d-flex flex-column align-items-center text-center">
                    <img src={user?.avatarLink} alt="avatar" className="rounded-circle" width="150"/>
                    <div className="mt-3">
                      <h4>{user?.name}</h4>
                      <p className="text-secondary mb-1">{user?.username}</p>
                      <p className="text-muted font-size-sm">Member since { new Date(user?.creationDate!).toDateString().substring(4) }</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-md-8">
              <div className="profile-card card mb-3">
                <div className="card-body">
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Cards Collected</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {_getCollectionSize()}
                    </div>
                  </div>
                  <hr/>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Unique Cards Collected</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {collection?.cards.length}
                    </div>
                  </div>
                  <hr/>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Unique Tags Assigned</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {tags?.length}
                    </div>
                  </div>
                  <hr/>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Total Collection Value</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      ${_roundPrice(_getCollectionValue())}
                    </div>
                  </div>
                  <hr/>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Decks Created</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {decks.length}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="row gutters-sm justify-content-center">
            <div className="col-sm-6 mb-3">
              <div className="profile-card card h-100">
                <div className="card-body">
                  <HighchartsReact highcharts={Highcharts} options={priceGraphOptions} />
                </div>
              </div>
            </div>
            <div className="col-sm-6 mb-3">
              <div className="profile-card card h-100">
                <div className="card-body">
                  <HighchartsReact highcharts={Highcharts} options={deckPercentageOptions} />
                </div>
              </div>
            </div>
          </div>
        </div>
      }
    </div>
  )
}


export default Profile
