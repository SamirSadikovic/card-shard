import { ChangeEvent, useEffect, useState } from 'react';
import { CollectedCard, Collection } from "../../utils/types"
import useCardsByIds from '../../hooks/useCardsByIds';

type Props = {
  collection: Collection,
  activeTags: string[],
  onDeleteClick: (deletedCard: CollectedCard) => void;
  // collectedCards: CollectedCard[],
  // cardInfo: Card[]
}

const CollectionBrowser = ({ collection, activeTags, onDeleteClick }: Props) => {
  const cardIds = collection.cards.map(c => c.id.cardId);

  const { data: cardInfo, isLoading } = useCardsByIds(cardIds);
  const [displayCards, setDisplayCards] = useState(collection.cards);
  const [filters, setFilters] = useState({
    search: '',
    activeTags: activeTags
  });

  useEffect(() => {
    setFilters({
      search: filters.search,
      activeTags: activeTags
    })
  }, [activeTags]);

  const search = (e: ChangeEvent<HTMLInputElement>) => {
    setFilters({
      search: e.target.value.toLowerCase(),
      activeTags: filters.activeTags
    })
  }

  const tagChecker = (array: string[], target: string[]) => {
    return target.every(v => array.includes(v));
  }

  useEffect(() => {
    const collectedCardsFilteredTags = collection.cards.filter(c => tagChecker(c.tags, filters.activeTags));

    if(filters.search === ""){
      setDisplayCards(collectedCardsFilteredTags);
      return;
    }

    const filteredCards = cardInfo?.filter(card => card.name.toLowerCase().includes(filters.search));
    const collectedCardsFiltered = collectedCardsFilteredTags.filter(collectedCard => filteredCards?.map(filteredCard => filteredCard.id).includes(collectedCard.id.cardId));
    
    setDisplayCards(collectedCardsFiltered);
  }, [filters]);

  const _onDeleteClick = (card: CollectedCard) => {
    // const updatedDisplayCards = displayCards.filter(c => c !== card);
    // setDisplayCards(updatedDisplayCards);
    onDeleteClick(card);
  }

  const _getName = (displayCard: CollectedCard) => {
    return cardInfo?.find(c => c.id == displayCard.id.cardId)?.name;
  }

  const _getPrice = (displayCard: CollectedCard) => {
    const price = cardInfo?.find(c => c.id == displayCard.id.cardId)!.cardSets.find(s => s.setCode === displayCard.id.setCode)?.setPrice;
    return (price === "0" || price === "0.0" || price === "0.00")? "N/A" : price;
  }

  console.log("Card IDs " + cardIds);
  console.log("Card info ");
  console.log(cardInfo);
  console.log(displayCards);
  return (
    <>
      <h3 className="text-center">Collection</h3>
      <hr/>
      {
        isLoading &&
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      }
      {
        !isLoading && cardInfo && displayCards &&
        <div className="container-sm">
          <input
            type="text"
            className="form-control"
            onChange={ search }
            placeholder='Search collection'
          ></input>
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">Quantity</th>
                <th scope="col">Name</th>
                <th scope="col">Set Code</th>
                <th scope="col">Rarity</th>
                <th scope="col">Price</th>
                <th scope="col">Options</th>
              </tr>
            </thead>
            <tbody>
              {displayCards.map((displayCard, index) => (
                <tr key={ index }>
                  <td>{ displayCard.quantity }</td>
                  {/* <td>{ cardInfo?.find(c => c.id == displayCard.id.cardId)!.name }</td> */}
                  <td>{ _getName(displayCard) }</td>
                  <td>{ displayCard.id.setCode }</td>
                  <td>{ displayCard.id.setRarity }</td>
                  <td>{ _getPrice(displayCard) }</td>
                  <td><button type="button" className="btn btn-danger" onClick={() => { _onDeleteClick(displayCard) } }>Delete</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      }
      {
        !isLoading && !cardInfo && displayCards.length == 0 &&
        <div className="row justify-content-center">
            <div className="col-12 col-md-3 m-3">
                <div className="alert" role="alert">
                    <p className="mb-0 text-center">
                        Your collection is empty
                    </p>
                </div>
            </div>
        </div>
      }
  </>
  )
}

export default CollectionBrowser
