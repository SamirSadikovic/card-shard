import { Link } from "react-router-dom"
import { User } from "../../utils/types"

type Props = {
    user: User
}

const DeckCard = ({ user }: Props) => {
   return (
    <section className="vh-100">
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-md-4 col-xl-4">
            <div className="card">
              <div className="card-body text-center">
                <h4 className="mb-2">{ user.name }</h4>
                <p className="text-muted">{ user.username }<br/>{ user.address }<br/>Member since { user.creationDate.toDateString().substring(4) }</p>
                <div className="mb-4 pb-2">
                  <Link className="btn btn-outline-primary btn-floating m-1" to="/collections">Collection</Link>
                  <Link className="btn btn-outline-primary btn-floating m-1" to="/decks">Decks</Link>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </section>
   )
}


export default DeckCard