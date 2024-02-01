import { Link } from "react-router-dom"
import { Deck } from "../../utils/types"
import useDeleteDeck from "../../hooks/useDeleteDeck"
import ConfirmDeleteModal from "../ConfirmDeleteModal"
import { useState } from "react"

type Props = {
    deck: Deck
}

const DeckCard = ({ deck }: Props) => {
    const [displayConfirmationModal, setDisplayConfirmationModal] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState("");
    const deleteDeck = useDeleteDeck(deck?.id!);
    
    const showDeleteModal = () => {
        setDeleteMessage(`Are you sure you want to delete ${deck.name}?`)
        setDisplayConfirmationModal(true);
    };

    const submitDelete = () => {
        deleteDeck.mutate();
        setDisplayConfirmationModal(false);
    };

    return (
        <div className="m-3">
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title mb-3 text-center">{ deck.name }</h5>
                    <p className="card-text">
                        <li className="list-group-item">Main deck: { deck.main.length } cards</li>
                        <li className="list-group-item">Extra deck: { deck.extra.length } cards</li>
                        <li className="list-group-item mb-3">Side deck: { deck.side.length } cards</li>
                        <li className="list-group-item">Date updated: { new Date(deck.creationDate).toDateString().substring(4) }</li>
                    </p>
                    <div className="text-center">
                        <Link className="btn btn-primary text-nowrap deck-card-button m-1" to={`/deckview/${deck.id}`}>View</Link>
                        <button className="btn btn-danger text-nowrap deck-card-button m-1" onClick={ showDeleteModal }>Delete</button>
                    </div>
                </div>
            </div>
            <ConfirmDeleteModal
                showModal={displayConfirmationModal}
                handleCancel={() => setDisplayConfirmationModal(false)}
                handleDelete={submitDelete}
                message={deleteMessage}
            />
        </div>
    )
}

export default DeckCard