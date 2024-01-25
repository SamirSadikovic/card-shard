import { Card } from "../../utils/types"
import { Link } from "react-router-dom"

type Props = {
  cards: Card[],
  pageNumber: number,
  onPreviewClick: (card: Card) => void
  onPageNumberChange: (pageNumber: number) => void;
}

const CardSearchResults = ({ cards, pageNumber, onPreviewClick, onPageNumberChange }: Props) => {
  return (
    <>
      {
        Array.isArray(cards) &&
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
              {cards.map((card, index) => (
                <tr key={ index } style={{cursor: 'pointer'}}>
                  <td>{ card.name }</td>
                  <td>{ card.type }</td>
                  <td title={card.desc}>
                    {card.desc.substring(0, 50)}
                    {card.desc.length > 50 && <div>...</div>}
                  </td>
                  <td>
                    <Link type="button" className="btn btn-primary" to={`/addcard/${card.id}`}>Add to...</Link></td><td>
                    <button type="button" className="btn btn-secondary" onClick={() => onPreviewClick(card)}>Preview</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="row justify-content-center">
            <div className="col-md-2">
              <button className="btn btn-secondary mb-2 mt-2" onClick={() => onPageNumberChange(pageNumber - 1) } disabled={ pageNumber == 1 }>Prev</button>
            </div>
            <div className="col-md-2">
                <div className=" mt-2 h5 text-center"><h3>{ pageNumber }</h3></div>
            </div>
            <div className="col-md-2">
              <button className="btn btn-secondary mb-2 mt-2" onClick={() => onPageNumberChange(pageNumber + 1) } disabled={ pageNumber == 1285 }>Next</button>
            </div>
          </div>
        </div>
      }
    </>

   )
}


export default CardSearchResults
