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
            {/* <div className="mt-3 mb-4">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
                className="rounded-circle img-fluid"/>
            </div> */}
            <h4 className="mb-2">{ user.name }</h4>
            <p className="text-muted">{ user.username }<br/>{ user.address }<br/>Member since { user.creationDate.toDateString().substring(4) }</p>
            <div className="mb-4 pb-2">
              <button type="button" className="btn btn-outline-primary btn-floating m-1">
                Collection
              </button>
              <button type="button" className="btn btn-outline-primary btn-floating m-1">
                Decks
              </button>
              <button type="button" className="btn btn-outline-primary btn-floating m-1">
                Wantlist
              </button>
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