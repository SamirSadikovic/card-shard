import { useState } from 'react';

import { Deck } from "../../utils/types"
import { Card } from "../../utils/types"
import CardObject from "../CardObject"

type Props = {
  deck: Deck,
  cardInfo: Card[],
  onCardClick: (card: Card) => void;
}

const DeckGrid = ({ deck, cardInfo, onCardClick }: Props) => {
  return (
    <div className="container-sm container-xsm">
      <h4>Main Deck</h4>
      <div className="row ptb-3-5">
        {deck.main.map((cardId, index) => (
          <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
            <CardObject
              key={ index }
              card={ cardInfo.find(c => c.id == cardId)! }
              handleClick={ onCardClick }
            />
          </div>
        ))}
      </div>
      <h4>Extra Deck</h4>
      <div className="row ptb-3-5">
        {deck.extra.map((cardId, index) => (
          <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
            <CardObject
              key={ index }
              card={ cardInfo.find(c => c.id == cardId)! }
              handleClick={ onCardClick }
            />
          </div>
        ))}
      </div>
      <h4>Side Deck</h4>
      <div className="row ptb-3-5">
        {deck.side.map((cardId, index) => (
          <div className="col-md-1 col-sm-2 col-6-5 text-center card-object-container grid-spacing" key={ index }>
            <CardObject
              key={ index }
              card={ cardInfo.find(c => c.id == cardId)! }
              handleClick={ onCardClick }
            />
          </div>
        ))}
      </div>
    </div>
   )
}


export default DeckGrid
