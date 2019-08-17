import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { ApprovalRuleService } from '../approval-rule.service';
import { ApprovalRule } from '../model/approval-rule.model';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent {

  constructor(private approvalRuleService: ApprovalRuleService,
              private router: Router) { }

  sendEvaluatios(approvalRule:ApprovalRule){
      this.approvalRuleService.create(approvalRule).subscribe((res) => {
        this.router.navigate(['/view'])
      });
  }


}
