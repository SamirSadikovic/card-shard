export type Card =  {
    name: string;
    type: string;
    desc: string;
    race: string;
    archetype: string;
    cardSets: string[];
    balistInfo: string[];
    imageLink: string;
}

export type Deck =  {
    name: string;
    main: number[];
    extra: number[];
    side: number[];
    creationDate: Date;
}

export type CollectedCard =  {
    name: string;
    setName: string;
    setCode: string;
    setRarity: string;
    setPrice: string;
    sellTrade: boolean;
    tags: string[];
}

export type Collection =  {
    cards: CollectedCard[];
}

export type User =  {
    name: string;
    email: string;
    username: string;
    address: string;
    creationDate: Date;
    userType: string;
}