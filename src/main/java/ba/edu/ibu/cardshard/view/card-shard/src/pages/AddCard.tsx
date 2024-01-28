import useCollections from "../hooks/useCollections";
import useCurrentUser from "../hooks/useCurrentUser";
import { useLayoutEffect, useState } from "react";
import useCardById from "../hooks/useCardById";
import { useForm } from "react-hook-form";
import CardPreview from "../components/CardPreview";
import useAddCard from "../hooks/useAddCard";
import { CardSet } from "../utils/types";
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import useDecksByUser from "../hooks/useDecksByUser";
import useUpdateDeck from "../hooks/useUpdateDeck";
import toast, { Toaster } from 'react-hot-toast';
import { useParams } from "react-router-dom";

export type CollectionCardAddFormData = {
    setCode: string,
    setRarity: string,
    quantity: number,
    sellTrade: string,
    tags: string
}

export type DeckCardAddFormData = {
    quantity: number,
    deckName: string,
    partOfDeck: string
}

const schemaCollection = yup
    .object({
        setCode: yup.string().required("Set code is required.").test({
            name: 'is-setCode',
            test(value, ctx) {
              if (value === "placeholder") {
                return ctx.createError({ message: 'Please select a set' })
              }
              return true
            }
        }),
        setRarity: yup.string().required("Password is required.").test({
            name: 'is-setRarity',
            test(value, ctx) {
              if (value === "placeholder") {
                return ctx.createError({ message: 'Please select a rarity' })
              }
              return true
            }
        }),
        quantity: yup.number().required("Quantity is required.").positive().integer(),
        sellTrade: yup.string().required("Sell/Trade info is required.").test({
            name: 'is-setRarity',
            test(value, ctx) {
              if (value === "placeholder") {
                return ctx.createError({ message: 'Please select a rarity' })
              }
              return true
            }
        }),
        tags: yup.string().required("Tags are required.")
    })
    .required()

const schemaDecks = yup
    .object({
        quantity: yup.number().required("Quantity is required.").positive().integer(),
        deckName: yup.string().required("Deck name is required.").test({
            name: 'is-deckName',
            test(value, ctx) {
              if (value === "placeholder") {
                return ctx.createError({ message: 'Please select a deck' })
              }
              return true
            }
        }),
        partOfDeck: yup.string().required("Part of deck is required").test({
            name: 'is-partOfDeck',
            test(value, ctx) {
              if (value === "placeholder") {
                return ctx.createError({ message: 'Please select a part of deck' })
              }
              return true
            }
        })
    })
    .required()



const AddCard = () => {
    const cardId = Number(useParams().id);
    const { data: user } = useCurrentUser();

    const { data: collection, isLoading: isLoadingCollections, isError: isErrorCollections } = useCollections(user?.id!);
    const { data: decks, isLoading: isLoadingDecks, isError: isErrorDecks } = useDecksByUser(user?.id!);
    const { data: card } = useCardById(cardId);

    const [rarities, setRarities] = useState<string[]>();
    const [cardSets, setCardSets] = useState<CardSet[]>();
    const addCard = useAddCard(collection?.id!);
    const updateDeck = useUpdateDeck();

    const { register: registerCollection, handleSubmit: handleSubmitCollection, formState: { errors } } = useForm<CollectionCardAddFormData>({
        resolver: yupResolver(schemaCollection)
    });
    
    const { register: registerDeck, handleSubmit: handleSubmitDeck } = useForm<DeckCardAddFormData>({
        resolver: yupResolver(schemaDecks)
    });

    useLayoutEffect(() => {
        setCardSets(card?.cardSets!);
    }, [card]);

    const onSetChange = (setCode: string) => {
        const sets = cardSets?.filter(s => s.setCode === setCode);
        setRarities(sets?.map(s => s.setRarity)!);
    }

    const addCardToCollection = (data: CollectionCardAddFormData) => {
        const tagArray = data.tags.split(',');

        const collectedCard = {
            id: {
                cardId: card?.id!,
                setCode: data.setCode,
                setRarity: data.setRarity
            },
            quantity: data.quantity,
            sellTrade: data.sellTrade === "Yes",
            tags: tagArray.map(t => t.trim())
        }

        addCard.mutate(collectedCard, {
            onSuccess: () => {
                toast.success(
                    `Successfully added ${card?.name} to collection!`,
                    {
                        duration: 3000
                    }
                );
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

    const addCardToDeck = (data: DeckCardAddFormData) => {
        const deckToUpdate = decks?.find(d => d.name === data.deckName);

        const updatedDeck = {
            id: deckToUpdate?.id!,
            userId: user?.id!,
            name: deckToUpdate?.name!,
            main: deckToUpdate?.main!,
            extra: deckToUpdate?.extra!,
            side: deckToUpdate?.side!,
            creationDate: deckToUpdate?.creationDate!,
            visibilityType: deckToUpdate?.visibilityType!,
        }

        if(data.partOfDeck === "Main"){
            for(var i = 0; i < data.quantity; i++)
                updatedDeck.main?.push(cardId)
        } else if (data.partOfDeck === "Side"){
            for(var i = 0; i < data.quantity; i++)
                updatedDeck.side?.push(cardId)
        }else if (data.partOfDeck === "Extra"){
            for(var i = 0; i < data.quantity; i++)
                updatedDeck.extra?.push(cardId)
        }



        updateDeck.mutate(updatedDeck, {
            onSuccess: () => {
                toast.success(
                    `Successfully added\n${card?.name}\nto ${updatedDeck.name}!`,
                    {
                        duration: 3000
                    }
                );
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
        <div className="row m-5">
            {
                // Loading data
                isLoadingCollections && isLoadingDecks &&
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            }
            {
                // Handle errors
                isErrorCollections && isErrorDecks &&
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
                !isLoadingCollections && !isLoadingDecks && card &&
                <>
                {/* <div className="row container-md mt-5 mx-auto"> */}
                    <div className="col-lg-3 mx-auto">
                        <h3 className="text-center">Collection</h3>
                        <hr/>
                        <form className="mb-2" onSubmit={handleSubmitCollection(addCardToCollection)}>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Name</label>
                                    <input type="text" className="form-control" disabled value={card?.name}/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Card Set</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...registerCollection("setCode")} onChange={(e) => onSetChange(e.target.value)}>
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
                                    <select className="form-control" defaultValue={"placeholder"} {...registerCollection("setRarity")}>
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
                                    <input type="number" className="form-control" placeholder="Quantity" {...registerCollection("quantity", { pattern: /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/ })}/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Sell/Trade</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...registerCollection("sellTrade")}>
                                        <option hidden disabled value={"placeholder"}>Select...</option>
                                        <option>Yes</option>
                                        <option>No</option>
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Tags</label>
                                    <input type="text" className="form-control" placeholder="Separate multiple tags by a comma" {...registerCollection("tags")}/>
                                    { errors.tags && <small style={{ color: "red" }}>{errors.tags.message}</small> }
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <button type="submit" className="btn btn-primary my-2">Add to Collection</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div className="col-lg-3 mx-auto">
                        <h3 className="text-center">Deck</h3>
                        <hr/>
                        <form className="mb-2" onSubmit={handleSubmitDeck(addCardToDeck)}>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Deck</label>
                                    <select className="form-control" defaultValue={"placeholder"} {...registerDeck("deckName")}>
                                        <option hidden disabled value={"placeholder"}>Select...</option>
                                        {decks?.map((deck, index) => (
                                            <option key={index}>{deck.name}</option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Quantity</label>
                                    <select className="form-control" defaultValue={1} {...registerDeck("quantity")}>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <label>Part of Deck</label>
                                    <select className="form-control" defaultValue={"Main"} {...registerDeck("partOfDeck")}>
                                        <option>Main</option>
                                        <option>Extra</option>
                                        <option>Side</option>
                                    </select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col form-group">
                                    <button type="submit" className="btn btn-primary my-2">Add to Deck</button>
                                </div>
                            </div>
                        </form>
                        <Toaster
                          position="bottom-center"
                        />
                    </div>
                    <div className="col-lg-2 mx-auto">
                        <h3 className="text-center">Preview</h3>
                        <hr/>
                        <CardPreview
                            card={card!}
                        />
                    </div>
                {/* </div> */}
                </>
            }
        </div>
    )
}


export default AddCard