
export interface Participant {
    id: number;
    name: string;
    email: string;
    hasPaid: boolean;
    pricePaid: number;
}

export interface ServiceParticipant {
    serviceId: number;
    name: string;
    description?: any;
    icon?: any;
    monthlyPrice?: any;
    participants: Participant[];
}
