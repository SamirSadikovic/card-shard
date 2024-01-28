import { useState } from "react"
import CardSearchForm from "../components/CardSearchForm"
import CardSearchResults from "../components/CardSearchResults"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"

const CardSearch = () => {
    const [cardsPerPage, setCardsPerPage] = useState(10);
    const [previewCard, setPreviewCard] = useState(defaultPreview);
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
    
    return (
        <div className="row m-5">
            <div className="col-lg-2">
                <h3 className="text-center">Search</h3>
                <hr className="mb-4 pb-2"/>
                <CardSearchForm
                    onSubmit={setCardFilterParams}
                />
            </div>
            <div className="col-lg-8">
                <h3 className="text-center">Results</h3>
                <hr/>
                <CardSearchResults
                    params={cardFilterParams}
                    cardsPerPage={cardsPerPage}
                    onPreviewClick={setPreviewCard}
                />
            </div>
            <div className="col-lg-2">
                <h3 className="text-center">Preview</h3>
                <hr className="mb-5 pb-2"/>
                <CardPreview
                    card={previewCard? previewCard : defaultPreview}
                />
                <div className="row mt-1 mx-auto">
                    <h5 className="col-12">Cards per page: </h5>
                    <select className="form-control" onChange={(c) => setCardsPerPage(Number(c.target.value))}>
                        <option>5</option>
                        <option selected>10</option>
                        <option>25</option>
                        <option>50</option>
                    </select>
                </div>
            </div>
        </div>
    )
}


export default CardSearch