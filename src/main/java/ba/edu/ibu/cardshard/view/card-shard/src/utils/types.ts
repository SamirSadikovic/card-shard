export type Card =  {
    id: number;
    name: string;
    type: string;
    desc: string;
    race: string;
    archetype: string;
    attribute: string;
    atk: number;
    def: number;
    level: number;
    scale: number;
    linkVal: number;
    linkMarkers: string[]
    cardSets: CardSet[];
    banlistInfo: Map<string, string>;
    imageLink: string;
}

export type CardSet = {
    setName: string;
    setCode: string;
    setRarity: string;
    setPrice: string;
}

export type CardPreview = {
    imageLink: string;
    name: string;
    desc: string;
}

export type Deck =  {
    id: string;
    userId: string;
    name: string;
    main: number[];
    extra: number[];
    side: number[];
    creationDate: Date;
    visibilityType: string;
}

export type DeckRequest =  {
    userId: string;
    name: string;
    main: number[];
    extra: number[];
    side: number[];
    visibilityType: string;
}

type CollectedCardId = {
    cardId: number,
    setCode: string,
    setRarity: string
}

export type CollectedCard =  {
    id: CollectedCardId;
    quantity: number;
    sellTrade: boolean;
    tags: string[];
}

export type Collection =  {
    id: string;
    userId: string;
    cards: CollectedCard[];
    visibilityType: string;
}

export type User =  {
    id: string;
    name: string;
    email: string;
    username: string;
    address: string;
    creationDate: Date;
    userType: string;
}

