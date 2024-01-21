import { Card } from "../../utils/types"

type Props = {
   card: {
    imageLink: string,
    name: string,
    desc: string
   };
}

const CardPreview = ({ card }: Props) => {
   return (
        <div className="card">
            <img src={ card.imageLink } className="card-img-top" alt={ card.name }/>
            <div className="card-body scroll">
                <p className="card-text">{ card.desc }</p>
            </div>
        </div>
   )
}

export default CardPreview