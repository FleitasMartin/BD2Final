import {Topic} from "./topic.model";
export class Evaluation   {
    date: string;
    gradeType: string;
    approvalScore: string;
    recoveryChances: number;
    promotable: boolean;
    approvalPromoScore: string;
    recoveryPromotable: boolean;
    topics: Topic[];
  }
