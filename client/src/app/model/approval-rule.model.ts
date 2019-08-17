import { Evaluation } from './evaluation.model';
export class ApprovalRule {
  id: string;
  version: number;
  matterCode:string;
  matterName:string;
  season:string;
  year:number;
  evaluations:Evaluation[];
}
