import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { ApprovalRuleService } from '../approval-rule.service';
import { ApprovalRule } from '../model/approval-rule.model';
import { switchMap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {
  approvalRule: ApprovalRule;
  constructor(private approvalRuleService: ApprovalRuleService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar) { }

  sendEvaluatios(approvalRule: ApprovalRule) {
    this.approvalRuleService.update(approvalRule).subscribe((res) => {
       this.router.navigate(['/view'])
    },
      err => {
        console.log(err);
        this.openSnackBar(err.status, 'Error');
      });
  }

  ngOnInit() {
    this.getApprovalRule();
  }

  getApprovalRule() {
    const id: string = this.route.snapshot.paramMap.get('id');
    this.approvalRuleService.get(id).subscribe(
      (res) => {
        this.approvalRule = res;
      },
      err => {
        console.log(err);
        this.openSnackBar(err.status, 'Error');
        this.router.navigate(['/']);
      }
    );
  }

  openSnackBar(message: any, action: string) {
    if(message == 409) message= 'Los datos ya han sido modificados. Presione F5 y luego modifique';
    if(message == 400) message= 'Id formato invalido';
    if(message == 404) message= 'No existe el id';
    this._snackBar.open(message, action, {
      duration: 10000,
    });
  }


}
