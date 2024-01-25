import { useState, ChangeEvent, useEffect } from "react"
import DeckCard from "../components/DeckCard"
import useDecksByUser from "../hooks/useDecksByUser";
import useCurrentUser from "../hooks/useCurrentUser";
import axios from "axios";
import CreateDeckDropdown from "../components/CreateDeckDropdown";
import useCreateDeck from "../hooks/useCreateDeck";

const DeckList = () => {
    const user = useCurrentUser();
    const { data: decks, isLoading, isError, error } = useDecksByUser(user.data?.id!);
    const [displayDecks, setDisplayDecks] = useState(decks);
    const createDeck = useCreateDeck();

    useEffect(() => {
        setDisplayDecks(decks);
    }, [decks]);

    const search = (e: ChangeEvent<HTMLInputElement>) => {
        const filteredDecks = decks?.filter(deck => deck.name.toLowerCase().includes(e.target.value.toLowerCase()));
        setDisplayDecks(filteredDecks);
    }

    console.log(displayDecks);
    return (
        <>
            <div className="container-md row mt-3 ms-3">
                <div className="col-md-2">
                    <CreateDeckDropdown
                        userId={user.data?.id!}
                        onDeckSave={
                            (createdDeck) => createDeck.mutate(createdDeck, {
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
                            }
                        })}
                    />
                </div>
                <div className="col-md-10">
                    <input
                        type="text"
                        className="form-control"
                        onChange={ search }
                        placeholder='Search decks...'
                    ></input>
                </div>
            </div>
            {
                isLoading &&
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            }
            {
                isError && axios.isAxiosError(error) && error.message != "Request failed with status code 404" &&
                <div className="row">
                    <div className="col-12 col-md-3 m-3">
                        <div className="alert alert-danger" role="alert">
                            <p className="mb-0">
                                {error.message}
                            </p>
                        </div>
                    </div>
                </div>
            }
            {
                isError && axios.isAxiosError(error) && error.message == "Request failed with status code 404" &&
                <div className="row">
                    <div className="col-12">
                        <div className="alert" role="alert">
                            <p className="mb-0">
                                There are no decks for this user.
                            </p>
                        </div>
                    </div>
                </div>
            }
            {
                !isLoading && displayDecks &&
                <div className="row">
                    {
                        displayDecks?.map((displayDeck, index) => (
                            <div className="col-md-3" key={index}>
                            <DeckCard
                                deck={displayDeck}
                            />
                            </div>
                        ))
                    }
                </div>
            }
        </>
    )
}

export default DeckList