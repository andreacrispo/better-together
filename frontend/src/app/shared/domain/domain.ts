
export class Participant {
    id: number;
    name: string;
    email: string;
    hasPaid: boolean;
    pricePaid: number;
    yearPaid: number;
    monthPaid: number;
}

export class ServiceParticipant {
    serviceId?: number;
    name: string;
    description?: string;
    icon?: any;
    monthlyPrice?: number;
    participantNumber?: number;
    participants?: Participant[];

}


export enum ActionType {
    CREATE   = "CREATE",
    RETRIVE  = "RETRIVE",
    UPDATE   = "UPDATE",
    DELETE   = "DELETE",
}