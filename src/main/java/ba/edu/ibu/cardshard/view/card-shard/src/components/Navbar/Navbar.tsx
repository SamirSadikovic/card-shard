import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router-dom"
import { RootState } from "../../store"
import { logout } from "../../store/authSlice";

const Navbar = () => {
    const { userToken } = useSelector((state: RootState) => state.auth);
    const dispatch = useDispatch();

    return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/collections">Card-Shard</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item border-start">
                            <Link className="nav-custom nav-link" to="/collections">Collection</Link>
                        </li>
                        <li className="nav-item border-start">
                            <Link className="nav-custom nav-link" to="/decklist">Decks</Link>
                        </li>
                        <li className="nav-item border-start">
                            <Link className="nav-custom nav-link" to="/cardsearch">Card Search</Link>
                        </li>
                        <li className="nav-item border-start">
                            <Link className="nav-custom nav-link" to="/profile">Profile</Link>
                        </li>
                        {
                            !userToken ? (
                                <>
                                    <li className="nav-item border-start">
                                        <Link className="nav-custom nav-link" to="/login">Login</Link>
                                    </li>
                                    <li className="nav-item border-start border-end">
                                        <Link className="nav-custom nav-link" to="/registration">Register</Link>
                                    </li>
                                </>
                            ) : (
                                    <li className="nav-item border-start border-end">
                                        <Link
                                            className="nav-custom nav-link" 
                                            style={{ color: "red"}}
                                            onClick={() => dispatch(logout())}
                                            to="/login"
                                        >
                                            Logout
                                        </Link>
                                    </li>
                            )
                        }
                    </ul>
                    </div>
                </div>
            </nav>
    )
}


export default Navbar