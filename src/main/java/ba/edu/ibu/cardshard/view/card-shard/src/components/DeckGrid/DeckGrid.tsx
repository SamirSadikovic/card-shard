import { useParams } from "react-router-dom"
import useCardsByIds from "../../hooks/useCardsByIds"
import useDeckById from "../../hooks/useDeckById"
import useUpdateDeck from "../../hooks/useUpdateDeck"
import { Card } from "../../utils/types"
import CardObject from "../CardObject"
import { defaultPreview } from "../../constants"

type Props = {
  previewCardClick: (card: Card) => void
}

const DeckGrid = ({ previewCardClick }: Props) => {
  const deckId  = useParams().id;
  const { data: deck, isLoading: isDeckLoading, isError: isDeckError } = useDeckById(deckId!);

  const cardIds = (deck?.main.concat(deck.side).concat(deck.extra).length != 0) ? deck?.main.concat(deck.side).concat(deck.extra) : [defaultPreview.id];
  const { data: cardInfo, isLoading: isCardInfoLoading, isError: isCardInfoError } = useCardsByIds(cardIds!);

  const updateDeck = useUpdateDeck();
  
  const deleteCardClick = (index: number, partOfDeck: string) => {
    const updatedDeck = deck!;

    if(partOfDeck === "main"){
        updatedDeck.main.splice(index, 1);
    } else if (partOfDeck === "side"){
        updatedDeck.side.splice(index, 1);
    }else if (partOfDeck === "extra"){
        updatedDeck.extra.splice(index, 1);
    }

    updateDeck.mutate(updatedDeck, {
        onSuccess: () => {
            // navigate('/cardsearch');
        },
        onError: () => {
            <div className="row">
                <div className="col-12 col-md-3 m-3">
                    <div className="alert alert-danger" role="alert">
                        <p className="mb-0">
                        Something went wrong, please try again.
                        </p>
                    </div>
                </div>
            </div>
        },
        onSettled() {
          // handle end
        },
      });
  }

  return (
    <>
      {
          // Loading data
          (isDeckLoading || isCardInfoLoading) &&
          <div className="spinner-border text-primary" role="status">
              <span className="visually-hidden">Loading...</span>
          </div>
      }
      {
          // Handle errors
          (isDeckError || isCardInfoError) &&
          <div className="row">
              <div className="col-12 col-md-3 m-3">
                  <div className="alert alert-danger" role="alert">
                      <p className="mb-0">
                          Something went wrong, please try again.
                      </p>
                  </div>
              </div>
          </div>
      }
      {
        // If not loading, and not error, show data
        !isDeckLoading && !isCardInfoLoading && deck && cardInfo &&
        <div className="container-sm container-xsm">
          <h3 className="text-center">{deck.name}</h3>
          <hr/>
          <h3>Main Deck</h3>
          <div className="row ptb-3-5 ms-1">
            {
              (deck.main.length != 0) ? (
              deck?.main.map((cardId, index) => (
                <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
                  <CardObject
                    key={ index }
                    card={ cardInfo?.find(c => c.id == cardId)! }
                    previewCardClick={previewCardClick}
                    deleteCardClick={() => deleteCardClick(index, "main")}
                  />
                </div>
              ))) : (
              <p className="text-muted">There are no cards in the main deck.</p>
              )
            }
          </div>
          <h3>Extra Deck</h3>
          <div className="row ptb-3-5 ms-1">
            {
              (deck.extra.length != 0) ? (
              deck?.extra.map((cardId, index) => (
                <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
                  <CardObject
                    key={ index }
                    card={ cardInfo?.find(c => c.id == cardId)! }
                    previewCardClick={previewCardClick}
                    deleteCardClick={() => deleteCardClick(index, "extra")}
                  />
                </div>
              ))) : (
              <p className="text-muted">There are no cards in the extra deck.</p>
              )
            }
          </div>
          <h3>Side Deck</h3>
          <div className="row ptb-3-5 ms-1">
            {
              (deck.side.length != 0) ? (
              deck?.side.map((cardId, index) => (
                <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
                  <CardObject
                    key={ index }
                    card={ cardInfo?.find(c => c.id == cardId)! }
                    previewCardClick={previewCardClick}
                    deleteCardClick={() => deleteCardClick(index, "side")}
                  />
                </div>
            ))) : (
              <p className="text-muted">There are no cards in the side deck.</p>
            )
            }
          </div>
        </div>
      }
    </>
   )
}


export default DeckGrid
