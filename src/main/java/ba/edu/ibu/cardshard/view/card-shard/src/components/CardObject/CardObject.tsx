import { MouseEvent } from "react";
import { Card } from "../../utils/types"

type Props = {
   key: number,
   card: Card,
   previewCardClick: (card: Card) => void,
   deleteCardClick: () => void,
}

const CardObject = ({ card, previewCardClick, deleteCardClick }: Props) => {
   const handleClick = (e: MouseEvent<HTMLImageElement, globalThis.MouseEvent>) => {
      if (e.type === 'click') {
         previewCardClick(card);
       } else if (e.type === 'contextmenu') {
         e.preventDefault();
         deleteCardClick();
       }
   };

   return (
      <img 
         src={ card.imageLink }
         className="img-fluid deck-card"
         alt={ card.name }
         onClick={ handleClick }
         onContextMenu={ handleClick }
      />
   )
}

export default CardObject
