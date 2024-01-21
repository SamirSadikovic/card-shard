import CardPreview from "./components/CardPreview"
import DeckCard from "./components/DeckCard"
import ProfileCard from "./components/ProfileCard"
import { Card, CollectedCard, Collection, Deck, User } from "./utils/types"

function App() {
  return (
    <>
      {/* <CardPreview card={card}/> */}
      {/* <DeckCard deck={deck}/> */}
      <ProfileCard user={user}/>
    </>
  )
}

const card: Card = {
  name: "Pot of Greed",
  type: "Spell Card",
  desc: "Draw 2 cards.",
  race: "Normal",
  archetype: "Greed",
  cardSets: ["BP02-EN129", "BP01-EN034"],
  balistInfo: ["Banned", "Banned", "Banned"],
  imageLink: "src/assets/img/55144522.jpg"
}

const deck: Deck =  {
  name: "Runick Musket",
  main: [1,2,3,4],
  extra: [71791814,2,3,4],
  side: [1,2,3,4],
  creationDate: new Date("2023-12-07T09:37:28.031+00:00")
}

const collectedCard1: CollectedCard =  {
  name: "Exodia the Forbidden One",
  setName: "Legend of Blue Eyes White Dragon",
  setCode: "LOB-EN124",
  setRarity: "Ultra Rare",
  setPrice: "88.16",
  sellTrade: false,
  tags: ["old", "anime"]
}

const collectedCard2: CollectedCard =  {
  name: "Right Arm of the Forbidden One",
  setName: "Legend of Blue Eyes White Dragon",
  setCode: "LOB-EN122",
  setRarity: "Ultra Rare",
  setPrice: "39.99",
  sellTrade: true,
  tags: ["old", "anime"]
}

const user: User =  {
  name: "Samir Sadikovic",
  email: "samir.sadikovic@stu.ibu.edu.ba",
  username: "samir.sadikovic.1",
  address: "Tuzla, Bosnia and Herzegovina",
  creationDate: new Date("2023-11-26T20:30:02.223Z"),
  userType: "COLLECTOR"
}

const collection: Collection =  {
  cards: [collectedCard1, collectedCard2]
}

export default App
