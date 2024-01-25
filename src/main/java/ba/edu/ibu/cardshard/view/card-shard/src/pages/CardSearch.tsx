import { useState } from "react"
import useCardFilter from "../hooks/useCardFilter"
import CardSearchForm from "../components/CardSearchForm"
import CardSearchResults from "../components/CardSearchResults"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"
import { CardFilterFormData } from "../components/CardSearchForm/CardSearchForm"

const CardSearch = () => {
    const [pageNumber, setPageNumber] = useState(1);

    const [cardFilterParams, setCardFilterParams] = useState({
        text: "DEFAULT",
        type: "DEFAULT",
        race: "DEFAULT",
        monsterType: "DEFAULT",
        monsterFrame: "DEFAULT",
        attribute: "DEFAULT",
        levelRankLinkVal: -1,
        scale: -1,
        atk: -1,
        def: -1,
        linkMarkers: ["DEFAULT"]
    });

    const { data: cards, isLoading, isError } = useCardFilter(cardFilterParams, (pageNumber-1));
    const [previewCard, setPreviewCard] = useState(defaultPreview);

    const _updateSearchParams = (params: CardFilterFormData) => {
        setCardFilterParams(params);
        setPageNumber(1);
    }
    
    return (
        <div className="m-2">
            {
                isLoading &&
                <div className="justify-content-center text-center">
                    <div className="spinner-border text-primary" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            }
            {
                isError &&
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
                !isLoading &&
                <div className="row">
                    <div className="col-md-2">
                        <CardSearchForm
                            onSubmit={_updateSearchParams}
                        />
                    </div>
                    <div className="col-md-8">
                        <CardSearchResults
                            cards={cards!}
                            pageNumber={pageNumber}
                            onPreviewClick={setPreviewCard}
                            onPageNumberChange={setPageNumber}
                        />
                    </div>
                    <div className="col-md-2">
                        <CardPreview
                            card={previewCard? previewCard : defaultPreview}
                        />
                    </div>
                </div>
            }
        </div>
    )
}


export default CardSearch