import * as yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { DeckRequest } from "../../utils/types";

type Props = {
    userId: string,
    onDeckSave: (data: DeckRequest) => void;
}

export type CreateDeckFormData = {
    name: string
}

const schema = yup
    .object({
        name: yup.string().required("Deck name is required.")
    })
    .required()

const DeckCard = ({ userId, onDeckSave }: Props) => {
    const { register, handleSubmit, formState: { errors } } = useForm<CreateDeckFormData>({
        resolver: yupResolver(schema)
    })

    const _onDeckSave = (data: CreateDeckFormData) => {
        const createdDeck = {
            userId: userId,
            name: data.name,
            main: [],
            extra: [],
            side: [],
            visibilityType: "PUBLIC"
        }
        onDeckSave(createdDeck);
    }

    return (
        <>
            <div className="create-deck-accordion accordion" id="createDeckAccordion">
                <div className="accordion-item">
                    <h2 className="accordion-header" id="headingOne">
                        <button className="create-deck-button accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#createDeckForm" aria-expanded="true" aria-controls="createDeckForm">
                        Create Deck
                        </button>
                    </h2>
                    <div id="createDeckForm" className="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#createDeckAccordion">
                        <div className="accordion-body">
                            <form onSubmit={handleSubmit(_onDeckSave)}>
                                <input type="text" className="form-control mb-2" placeholder="Deck name..." {...register("name")}/>
                                { errors.name && <small style={{ color: "red" }}>{errors.name.message}</small> }
                                <button type="submit" className="btn btn-primary">Create</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default DeckCard