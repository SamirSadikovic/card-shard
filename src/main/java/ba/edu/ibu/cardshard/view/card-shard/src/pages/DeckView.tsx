import { useParams } from "react-router-dom"
import useCardsByIds from "../hooks/useCardsByIds"
import useDeckById from "../hooks/useDeckById"
import DeckGrid from "../components/DeckGrid"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"
import { useState } from "react"
import useUpdateDeck from "../hooks/useUpdateDeck"

const DeckView = () => {
    const deckId  = useParams().id;
    const { data: deck, isLoading: isDeckLoading, isError: isDeckError } = useDeckById(deckId!);

    const cardIds = deck?.main.concat(deck.side).concat(deck.extra);
    const { data: cardInfo, isLoading: isCardInfoLoading, isError: isCardInfoError } = useCardsByIds(cardIds!);

    const [previewCard, setPreviewCard] = useState(defaultPreview);
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

    if (!deck) {
        return (
            <p className="m-2">The requested deck does not exist.</p>
        )
    }

    if (deck.main.length == 0 && deck.side.length == 0 && deck.extra.length == 0) {
        return (
            <p className="m-2">There are no cards in this deck</p>
        )
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
                <div className="row m-1">
                    <div className="col-md-3">
                        <CardPreview
                            card={previewCard!}
                        />
                    </div>
                    <div className="col-md-9">
                        <DeckGrid
                            deck={deck}
                            cardInfo={cardInfo}
                            previewCardClick={setPreviewCard}
                            deleteCardClick={deleteCardClick}
                        />
                    </div>
                </div>
            }
        </>
    )
}


export default DeckView