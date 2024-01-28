import { ChangeEvent, useEffect, useState } from 'react';
import { Card, CollectedCard, Collection } from "../../utils/types"
import useCardsByIds from '../../hooks/useCardsByIds';
import ConfirmDeleteModal from '../ConfirmDeleteModal';

type Props = {
  collection: Collection,
  activeTags: string[],
  cardsPerPage: number,
  onPreviewClick: (card: Card) => void,
  onDeleteClick: (deletedCard: CollectedCard) => void
}

const CollectionBrowser = ({ collection, activeTags, cardsPerPage, onPreviewClick, onDeleteClick }: Props) => {
  const [displayConfirmationModal, setDisplayConfirmationModal] = useState<boolean>(false);
  const [deleteMessage, setDeleteMessage] = useState("");
  const [deletedCard, setDeletedCard] = useState<CollectedCard>();

  const cardIds = collection.cards.map(c => c.id.cardId);
  const { data: cardInfo, isLoading } = useCardsByIds(cardIds);
  const [filterCards, setFilterCards] = useState(collection.cards);

  //Pagination
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [displayCards, setDisplayCards] = useState<CollectedCard[]>();


  const [filters, setFilters] = useState({
    search: '',
    activeTags: activeTags
  });

  useEffect(() => {
    setFilters({
      search: filters.search,
      activeTags: activeTags
    })
  }, [activeTags]);

  useEffect(() => {
    setCurrentPage(1);
    setTotalPages(Math.ceil(collection.cards.length / cardsPerPage));
  }, [collection]);

  useEffect(() => {
    const indexLast = currentPage * cardsPerPage;
    const indexFirst = indexLast - cardsPerPage;
    setDisplayCards(filterCards.slice(indexFirst, indexLast));
    setTotalPages(Math.ceil(filterCards.length / cardsPerPage));
  }, [filterCards, currentPage, cardsPerPage]);

  const search = (e: ChangeEvent<HTMLInputElement>) => {
    setFilters({
      search: e.target.value.toLowerCase(),
      activeTags: filters.activeTags
    })
  }

  const tagChecker = (array: string[], target: string[]) => {
    return target.every(v => array.includes(v));
  }

  useEffect(() => {
    const collectedCardsFilteredTags = collection.cards.filter(c => tagChecker(c.tags, filters.activeTags));

    if(filters.search === ""){
      setFilterCards(collectedCardsFilteredTags);
      return;
    }

    const filteredCards = cardInfo?.filter(card => card.name.toLowerCase().includes(filters.search));
    const collectedCardsFiltered = collectedCardsFilteredTags.filter(collectedCard => filteredCards?.map(filteredCard => filteredCard.id).includes(collectedCard.id.cardId));
    
    setFilterCards(collectedCardsFiltered);
  }, [filters]);

  const _getCardInfo = (displayCard: CollectedCard) => {
    return cardInfo?.find(c => c.id == displayCard.id.cardId);
  }

  const _getName = (displayCard: CollectedCard) => {
    return cardInfo?.find(c => c.id == displayCard.id.cardId)?.name;
  }

  const _getPrice = (displayCard: CollectedCard) => {
    const price = cardInfo?.find(c => c.id == displayCard.id.cardId)!.cardSets.find(s => s.setCode === displayCard.id.setCode)?.setPrice;
    return (price === "0" || price === "0.0" || price === "0.00")? "N/A" : "$" + price;
  }

  const showDeleteModal = (deletedCard: CollectedCard) => {
    console.log(deletedCard);
    setDeleteMessage(`Are you sure you want to remove ${_getName(deletedCard)} from your collection?`);
    setDeletedCard(deletedCard);
    setDisplayConfirmationModal(true);
  };

  const submitDelete = () => {
    const updatedFilterCards = filterCards.filter(c => c !== deletedCard);
    setFilterCards(updatedFilterCards);
    onDeleteClick(deletedCard!);
    setDeletedCard(undefined);
    setDisplayConfirmationModal(false);
  };

  return (
    <>
      <h3 className="text-center">Collection</h3>
      <hr/>
      {
        isLoading &&
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      }
      {
        !isLoading && cardInfo && filterCards &&
        <div className="container-sm">
          <input
            type="text"
            className="form-control mb-1"
            onChange={ search }
            placeholder='Search collection...'
          ></input>
          <table className="table table-striped" id="collectionTable">
            <thead>
              <tr>
                <th scope="col">Quantity</th>
                <th scope="col">Name</th>
                <th scope="col">Set Code</th>
                <th scope="col">Rarity</th>
                <th scope="col">Price</th>
                <th scope="col">Options</th>
              </tr>
            </thead>
            <tbody>
              {displayCards?.map((displayCard, index) => (
                <tr key={ index }>
                  <td>{ displayCard.quantity }</td>
                  <td>{ _getName(displayCard) }</td>
                  <td>{ displayCard.id.setCode }</td>
                  <td>{ displayCard.id.setRarity }</td>
                  <td>{ _getPrice(displayCard) }</td>
                  <td>
                    <button type="button" className="btn btn-danger" onClick={() => { showDeleteModal(displayCard) } }>Delete</button>
                  </td>
                  <td>
                    <button type="button" className="btn btn-secondary" onClick={() => onPreviewClick(_getCardInfo(displayCard)!)}>Preview</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
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
          <ConfirmDeleteModal
                showModal={displayConfirmationModal}
                handleCancel={() => setDisplayConfirmationModal(false)}
                handleDelete={submitDelete}
                message={deleteMessage}
            />
        </div>
      }
      {
        !isLoading && !cardInfo && filterCards.length == 0 &&
        <div className="row justify-content-center">
            <div className="col-12 col-md-3">
              <p className="text-center text-muted">Your collection is empty</p>
            </div>
        </div>
      }
  </>
  )
}

export default CollectionBrowser
