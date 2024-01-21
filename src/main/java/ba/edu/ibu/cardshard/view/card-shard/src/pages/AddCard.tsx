import { useNavigate, useParams } from "react-router-dom";
import useCollections from "../hooks/useCollections";
import useCurrentUser from "../hooks/useCurrentUser";
import { useLayoutEffect, useState } from "react";
import useCardById from "../hooks/useCardById";
import { useForm } from "react-hook-form";
import CardPreview from "../components/CardPreview";
import useAddCard from "../hooks/useAddCard";
import { CardSet } from "../utils/types";

export type CollectionCardAddFormData = {
    cardInfo: {
        setCode: string,
        setRarity: string,
        quantity: number,
        sellTrade: string,
        tags: string
    }
}

// export type DeckCardAddFormData = {
//     cardId: number,
//     deckId: string,
//     partOfDeck: string
// }

const AddCard = () => {
    const cardId = Number(useParams().id);
    const user = useCurrentUser();
    const navigate = useNavigate()

    const { data: collection, isLoading, isError } = useCollections(user.data?.id!);
    const { data: card } = useCardById(cardId);

    const [rarities, setRarities] = useState<string[]>();
    const [cardSets, setCardSets] = useState<CardSet[]>();
    const addCard = useAddCard(collection?.id!);

    const { register, handleSubmit, formState: { errors } } = useForm<CollectionCardAddFormData>();

    // const { addToDeck, handleDeckSubmit } = useForm<DeckCardAddFormData>();
    // console.log(card);

    // useEffect(() => {
        
    // }, [rarities, selectedSet, collection, card])

    useLayoutEffect(() => {
        setCardSets(card?.cardSets!);
    }, [card]);

    const onSetChange = (setCode: string) => {
        const sets = cardSets?.filter(s => s.setCode === setCode);
        setRarities(sets?.map(s => s.setRarity)!);
    }

    const addCardToCollection = (data: CollectionCardAddFormData) => {
        const tagArray = data.cardInfo.tags.split(',');

        const collectedCard = {
            id: {
                cardId: card?.id!,
                setCode: data.cardInfo.setCode,
                setRarity: data.cardInfo.setRarity
            },
            quantity: data.cardInfo.quantity,
            sellTrade: data.cardInfo.sellTrade === "Yes",
            tags: tagArray.map(t => t.trim())
        }

        addCard.mutate(collectedCard, {
            onSuccess: () => {
                navigate('/cardsearch');
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
                isLoading &&
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            }
            {
                // Handle errors
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
                // If not loading, and not error, show data
                !isLoading && card &&
                <div className="row container-md mt-5">
                    <div className="col-lg-4 m-auto border border-secondary rounded">
                        <h3 className="text-center">Add to collection</h3>
                        <hr/>
                        <form className="mb-2" onSubmit={handleSubmit(addCardToCollection)}>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Name</label>
                                    <input type="text" className="form-control" disabled value={card?.name}/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Card Set</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...register("cardInfo.setCode")} onChange={(e) => onSetChange(e.target.value)}>
                                        <option hidden disabled value={"placeholder"}>Select...</option>
                                        {cardSets?.map((set, index) => (
                                            <option key={index} title={set.setName}>{set.setCode}</option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Rarity</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...register("cardInfo.setRarity")}>
                                        <option hidden disabled value={"placeholder"}>Select...</option>
                                        {rarities?.map((rarity, index) => (
                                            <option key={index}>{rarity}</option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Quantity</label>
                                    <input type="number" className="form-control" placeholder="Quantity" {...register("cardInfo.quantity", { pattern: /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/ })}/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Sell/Trade</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...register("cardInfo.sellTrade")}>
                                        <option hidden disabled value={"placeholder"}>Select...</option>
                                        <option>Yes</option>
                                        <option>No</option>
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Tags</label>
                                    <input type="text" className="form-control" placeholder="Separate multiple tags by a comma" {...register("cardInfo.tags")}/>
                                    { errors.cardInfo && <small style={{ color: "red" }}>{errors.cardInfo.message}</small> }
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <button type="submit" className="btn btn-primary my-2">Add Card</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    {/* <div className="col-lg-4 m-auto border border-secondary rounded">
                        <h3 className="text-center">Add to deck</h3>
                        <hr/>
                        <form className="mb-2">
        
                        </form>
                    </div> */}
                    <div className="col-lg-4 m-auto">
                        <CardPreview
                            card={card!}
                        />
                    </div>
                </div>
            }
        </>
    )
}


export default AddCard