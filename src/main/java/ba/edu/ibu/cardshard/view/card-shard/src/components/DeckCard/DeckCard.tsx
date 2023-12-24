import { Deck } from "../../utils/types"

type Props = {
    deck: Deck
}

const DeckCard = ({ deck }: Props) => {
   return (
       <div className="col-12 col-md-3 m-3">
           <div className="card">
               <div className="card-body">
                   <h5 className="card-title mb-3 text-center">{ deck.name }</h5>
                   <p className="card-text">
                       <li className="list-group-item">Main deck: { deck.main.length } cards</li>
                       <li className="list-group-item">Extra deck: { deck.extra.length } cards</li>
                       <li className="list-group-item mb-3">Side deck: { deck.side.length } cards</li>
                       <li className="list-group-item">Creation date: { deck.creationDate.toDateString().substring(4) }</li>
                   </p>
                   <a className="col-12 btn btn-primary">Edit</a>
               </div>
           </div>
       </div>
   )
}


export default DeckCard