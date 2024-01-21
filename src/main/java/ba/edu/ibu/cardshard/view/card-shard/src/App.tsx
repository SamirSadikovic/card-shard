import { AddCard, CardSearch, Collections, DeckView, DeckList, Login, NotFound, Profile, Registration } from "./pages"
import { Route, Routes } from "react-router-dom"
import Navbar from "./components/Navbar"
import ProtectedRoute from "./utils/ProtectedRoute"

import "./assets/css/CardPreview.css"
import "./assets/css/CardObject.css"
import "./assets/css/DeckGrid.css"
import "./assets/css/SearchResults.css"
import "./assets/css/CardSearchForm.css"
import "./assets/css/TagView.css"
import "./assets/css/Login.css"
import "./assets/css/Register.css"
import "./assets/css/Decks.css"
import "./assets/css/DeckCard.css"
import "./assets/css/Navbar.css"

function App() {
  return (
    <>
      <Navbar/>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/registration" element={<Registration />} />
        <Route element={<ProtectedRoute />}>
          <Route path="/profile" element={<Profile />} />
          <Route path="/cardsearch" element={<CardSearch />} />
          <Route path="/collections" element={<Collections />} />
          <Route path="/addcard/:id" element={<AddCard />} />
          <Route path="/decklist" element={<DeckList />} />
          <Route path="/deckview/:id" element={<DeckView />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  )
}

export default App