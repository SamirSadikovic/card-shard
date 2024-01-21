import { Card } from "../../utils/types"

type Props = {
   card: Card,
   handleClick: (card: Card) => void;
}

const CardObject = ({ card, handleClick }: Props) => {
   return (
      <img 
         src={ card.imageLink }
         className="img-fluid deck-card"
         alt={ card.name }
         onClick={() => handleClick(card)}
         />
   )
}

export default CardObject
