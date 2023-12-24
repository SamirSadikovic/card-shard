import { Card } from "../../utils/types"

type Props = {
   card: Card
}

const CardPreview = ({ card }: Props) => {
   return (
       <div className="col-12 col-md-3 m-3">
           <div className="card">
               <img src={ card.imageLink }></img>
           </div>
       </div>
   )
}


export default CardPreview