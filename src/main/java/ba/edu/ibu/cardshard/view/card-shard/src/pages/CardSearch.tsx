import { useState } from "react"
import useCardFilter from "../hooks/useCardFilter"
import CardSearchForm from "../components/CardSearchForm"
import CardSearchResults from "../components/CardSearchResults"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"

const CardSearch = () => {
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
        linkMarkers: ["DEFAULT"],
        pageNumber: 0
    });

    const { data: cards, isLoading, isError } = useCardFilter(cardFilterParams);
    const [previewCard, setPreviewCard] = useState(defaultPreview);

    // const modifyPageNumber = (pageNumber: number) =>{
    //     const newParams = cardFilterParams;
    //     newParams.pageNumber = pageNumber;
    //     console.log("Page number from method " + pageNumber);
    //     setCardFilterParams(newParams);
    // }
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
                            onSubmit={ setCardFilterParams }
                        />
                    </div>
                    <div className="col-md-8">
                        <CardSearchResults
                            cards={cards!}
                            onPreviewClick={ setPreviewCard }
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