import { useParams } from "react-router-dom"
import useCardsByIds from "../hooks/useCardsByIds"
import useDeckById from "../hooks/useDeckById"
import DeckGrid from "../components/DeckGrid"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"
import { useState } from "react"

const DeckView = () => {
    const deckId  = useParams().id;
    const { data: deck, isLoading: isDeckLoading, isError: isDeckError } = useDeckById(deckId!);

    const cardIds = deck?.main.concat(deck.side).concat(deck.extra);
    const { data: cardInfo, isLoading: isCardInfoLoading, isError: isCardInfoError } = useCardsByIds(cardIds!);

    const [previewCard, setPreviewCard] = useState(defaultPreview);

    if (!deck) {
        return (
            <p>The requested deck does not exist.</p>
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
                            onCardClick={setPreviewCard}
                        />
                    </div>
                </div>
            }
        </>
    )
}


export default DeckView