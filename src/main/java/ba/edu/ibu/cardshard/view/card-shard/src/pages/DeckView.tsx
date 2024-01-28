import { useNavigate } from "react-router-dom";
import DeckGrid from "../components/DeckGrid"
import CardPreview from "../components/CardPreview"
import { defaultPreview } from "../constants"
import { useState } from "react"

const DeckView = () => {
    const [previewCard, setPreviewCard] = useState(defaultPreview);
    const navigate = useNavigate();

    return (
        <div className="row m-5">
            <div className="col-lg-9">
                <DeckGrid
                    previewCardClick={setPreviewCard}
                />
            </div>
            <div className="col-lg-2">
                <h3 className="text-center">Preview</h3>
                <hr className="mb-5 pb-4"/>
                <CardPreview
                    card={previewCard!}
                />
                <button className="btn btn-secondary mt-2" onClick={() => navigate(-1)}>Back to Decks</button>
            </div>
        </div>
    )
}


export default DeckView