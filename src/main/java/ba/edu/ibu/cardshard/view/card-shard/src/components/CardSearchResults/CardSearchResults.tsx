import { useEffect, useState } from "react"
import useCardFilter from "../../hooks/useCardFilter"
import { Card } from "../../utils/types"
import { Link } from "react-router-dom"
import { CardFilterFormData } from "../CardSearchForm/CardSearchForm"

type Props = {
  params: CardFilterFormData,
  cardsPerPage: number,
  onPreviewClick: (card: Card) => void
}

const CardSearchResults = ({ params, cardsPerPage, onPreviewClick }: Props) => {
  const [pageNumber, setPageNumber] = useState(1);

  const { data: filterResponse, isLoading, isError } = useCardFilter(params, (pageNumber-1), cardsPerPage);
  
  useEffect(() => {
    setPageNumber(1);
  }, [params]);
  
  return (
    <>
      {
          isLoading &&
          <div className="justify-content-center text-center">
              <div className="spinner-border text-primary" role="status">
                  <span className="visually-hidden">Loading...</span>
              </div>
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
        !isLoading && Array.isArray(filterResponse?.cards) &&
        <div className="container-sm">
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">Name</th>
                <th scope="col">Type</th>
                <th scope="col">Text</th>
                <th scope="col" colSpan={2}>Options</th>
              </tr>
            </thead>
            <tbody>
              {filterResponse.cards.map((card, index) => (
                <tr key={ index } style={{cursor: 'pointer'}}>
                  <td>{ card.name }</td>
                  <td>{ card.type }</td>
                  <td title={card.desc}>
                    {card.desc.substring(0, 50)}
                    {card.desc.length > 50 && <>...</>}
                  </td>
                  <td>
                    <Link type="button" className="btn btn-primary" to={`/addcard/${card.id}`}>Add...</Link>
                  </td>
                  <td>
                    <button type="button" className="btn btn-secondary" onClick={() => onPreviewClick(card)}>Preview</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <nav>
            <ul className="pagination justify-content-center">
              <li className="page-item">
                <button className="btn btn-secondary" onClick={() => setPageNumber(pageNumber - 1) } disabled={ pageNumber == 1 }>Prev</button>
              </li>
              <li className="page-item"><a className="page-link ps-3 pe-3">Page { filterResponse.currentPage + 1} / { filterResponse.totalPages }</a></li>
              <li className="page-item">
                <button className="btn btn-secondary" onClick={() => setPageNumber(pageNumber + 1) } disabled={ pageNumber == filterResponse.totalPages }>Next</button>
              </li>
            </ul>
          </nav>
        </div>
      }
    </>

   )
}


export default CardSearchResults
