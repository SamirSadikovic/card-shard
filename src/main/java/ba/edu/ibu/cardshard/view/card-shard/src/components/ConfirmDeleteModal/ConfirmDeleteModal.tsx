import { Button, Modal } from "react-bootstrap";

type Props = {
    showModal: boolean,
    handleCancel: () => void,
    handleDelete: () => void,
    message: string
}

const ConfirmDeleteModal = ({ showModal, handleCancel, handleDelete, message }: Props) => {
    return (
        <>
            <Modal show={ showModal } onHide={ handleCancel }>
                <Modal.Header closeButton>
                    <Modal.Title>Delete Confirmation</Modal.Title>
                </Modal.Header>
                <Modal.Body><div className="alert alert-danger">{ message }</div></Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={ handleCancel }>
                        Cancel
                    </Button>
                    <Button variant="danger" onClick={ handleDelete }>
                        Delete
                    </Button>
                </Modal.Footer>
            </Modal>
      </>
    )
}
  
export default ConfirmDeleteModal;