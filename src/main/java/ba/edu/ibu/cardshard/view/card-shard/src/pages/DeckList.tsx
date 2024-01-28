import { useState, ChangeEvent, useEffect } from "react"
import DeckCard from "../components/DeckCard"
import useDecksByUser from "../hooks/useDecksByUser";
import useCurrentUser from "../hooks/useCurrentUser";
import CreateDeckDropdown from "../components/CreateDeckDropdown";
import useCreateDeck from "../hooks/useCreateDeck";
import { Deck } from "../utils/types";

const DeckList = () => {
    const { data: user } = useCurrentUser();
    const { data: decks, isLoading, isError } = useDecksByUser(user?.id!);
    const [filterDecks, setFilterDecks] = useState(decks);
    const createDeck = useCreateDeck();

    //Pagination
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [decksPerPage, setDecksPerPage] = useState(6);
    const [displayDecks, setDisplayDecks] = useState<Deck[]>();

    useEffect(() => {
        setFilterDecks(decks);
    }, [decks]);

    useEffect(() => {
        setCurrentPage(1);
        setTotalPages(Math.ceil(decks?.length! / decksPerPage));
      }, [decks]);
    
    useEffect(() => {
        const indexLast = currentPage * decksPerPage;
        const indexFirst = indexLast - decksPerPage;
        setDisplayDecks(filterDecks?.slice(indexFirst, indexLast));
        setTotalPages(Math.ceil(filterDecks?.length! / decksPerPage));
    }, [filterDecks, currentPage, decksPerPage]);

    const search = (e: ChangeEvent<HTMLInputElement>) => {
        const filteredDecks = decks?.filter(deck => deck.name.toLowerCase().includes(e.target.value.toLowerCase()));
        setFilterDecks(filteredDecks);
    }

    return (
        <div className="row m-5">
            {
                isLoading &&
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
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
                !isLoading && displayDecks &&
                <>
                    <div className="col-lg-2 mx-auto">
                        <div className="row">
                            <h3 className="text-center">Options</h3>
                            <hr/>
                            <input
                                type="text"
                                className="form-control mb-2"
                                onChange={ search }
                                placeholder='Search decks...'
                            ></input>
                            <CreateDeckDropdown
                                userId={user?.id!}
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
                            <h5 className="col-12 mt-1">Decks per page: </h5>
                            <select className="form-control" onChange={(c) => setDecksPerPage(Number(c.target.value))}>
                                <option>3</option>
                                <option selected>6</option>
                                <option>12</option>
                                <option>18</option>
                            </select>
                        </div>
                    </div>
                    <div className="col-lg-9 mx-auto">
                        <div className="row">
                            <h3 className="text-center">Decks</h3>
                            <hr/>
                            {
                                (displayDecks.length != 0) ? (
                                displayDecks?.map((displayDeck, index) => (
                                    <div className="col-md-4" key={index}>
                                    <DeckCard
                                        deck={displayDeck}
                                    />
                                    </div>
                                ))) : (
                                    <p className="text-center text-muted">You have no decks</p>
                                )
                            }
                            <nav>
                                <ul className="pagination justify-content-center">
                                    <li className="page-item">
                                        <button className="btn btn-secondary" onClick={() => setCurrentPage(currentPage - 1) } disabled={ currentPage == 1 }>Prev</button>
                                    </li>
                                    <li className="page-item"><a className="page-link ps-3 pe-3">Page { currentPage} / { totalPages }</a></li>
                                    <li className="page-item">
                                        <button className="btn btn-secondary" onClick={() => setCurrentPage(currentPage + 1) } disabled={ currentPage == totalPages }>Next</button>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </>
            }
        </div>
    )
}

export default DeckList