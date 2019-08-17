import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { ApprovalRule } from './model/approval-rule.model';
import {EMPTY, Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApprovalRuleService {
  baseUrl: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  create(approvalRule: ApprovalRule):Observable<ApprovalRule | any> {
    return this.httpClient.post(this.baseUrl + '/approval_rule', approvalRule, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  get(id:string ):Observable<ApprovalRule | any> {
    return this.httpClient.get(this.baseUrl + '/approval_rule/'+ id);
  }

  update(approvalRule: ApprovalRule):Observable<ApprovalRule | any> {
    return this.httpClient.patch(this.baseUrl + '/approval_rule', approvalRule, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  all():Observable<ApprovalRule[] | any>{
    return this.httpClient.get(this.baseUrl + '/approval_rule');
  }

  delete(id:string ):Observable<ApprovalRule | any> {
    return this.httpClient.delete(this.baseUrl + '/approval_rule/'+ id);
  }
}
