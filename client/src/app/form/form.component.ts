import { Component, OnInit, OnChanges, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Evaluation } from '../model/evaluation.model';
import { ApprovalRule } from '../model/approval-rule.model';
import { Topic } from '../model/topic.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit, OnChanges {

  @Input() approvalRule: ApprovalRule;
  @Output() onApprovalRuleChange: EventEmitter<ApprovalRule> = new EventEmitter();

  constructor(private fb: FormBuilder) { }

  evaluationForm: FormGroup;
  numberScores = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
  letterScores = ['A', 'B', 'C', 'D', 'E', 'F'];
  years = [2021,2020,2019,2018,2017,2016,2015];
  seasons = ['1er semestre', '2do semestre'];

  ngOnInit() {
    this.createForm(this.approvalRule);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (!!changes['approvalRule']) {
      this.createForm(this.approvalRule);
    }
  }

  createForm(approvalRule?: ApprovalRule) {
    this.evaluationForm = this.fb.group({
      id: [approvalRule && approvalRule.id || null],
      version: [approvalRule && approvalRule.version || null],
      matterCode: [approvalRule && approvalRule.matterCode || 'LS047'],
      matterName: [approvalRule && approvalRule.matterName || 'Matemática 4'],
      year: [approvalRule && approvalRule.year || null],
      season: [approvalRule && approvalRule.season || null],
      evaluations: this.createEvaluations(approvalRule && approvalRule.evaluations || null)
    })
  }

  createEvaluations(evaluations: Evaluation[]): FormArray {
    const formArray = this.fb.array([]);
    if (evaluations && evaluations.length > 0) {
      evaluations.forEach((e) => {
        formArray.push(this.createEvaluation(e))
      })
      return formArray;
    }
    return this.fb.array([this.createEvaluation(null)]);
  }

  createEvaluation(evaluation?: Evaluation) {
    return this.fb.group({
      date: [evaluation && evaluation.date || null, Validators.required],
      gradeType: [evaluation && evaluation.gradeType || 'NUMERIC', Validators.required],
      approvalScore: [evaluation && evaluation.approvalScore || 0, Validators.required],
      recoveryChances: [evaluation && evaluation.recoveryChances || 0, Validators.required],
      promotable: [evaluation && evaluation.promotable || false],
      approvalPromoScore: [evaluation && evaluation.approvalPromoScore || ''],
      recoveryPromotable: [evaluation && evaluation.promotable || false],
      topics: this.createTopics(evaluation),
    })
  }

  createTopics(evaluation?: Evaluation): FormArray {
    const formArray = this.fb.array([]);
    if (evaluation && evaluation.topics && evaluation.topics.length > 0) {
      evaluation.topics.forEach((t) => {
        formArray.push(this.createTopic(t))
      })
      return formArray;
    }
    return this.fb.array([this.createTopic()]);
  }

  createTopic(topic?: Topic) {
    return this.fb.group({
      name: [topic && topic.name || ''],
      weight: [topic && topic.weight || 0],
    })
  }

  get getEvaluations() {
    return this.evaluationForm.get('evaluations') as FormArray;
  }

  getTopics(form) {
    return form.controls.topics.controls;
  }


  addEvaluation() {
    this.getEvaluations.push(this.createEvaluation());
  }

  addTopic(i) {
    const control = (<FormArray>this.evaluationForm.get('evaluations') as FormArray).at(i).get('topics') as FormArray;
    control.push(this.createTopic());
  }

  deleteTopic(pointIndex, topicIndex) {
    const control = (<FormArray>this.evaluationForm.get('evaluations') as FormArray).at(pointIndex).get('topics') as FormArray;
    control.removeAt(topicIndex);
  }

  deleteEvaluation(index) {
    this.getEvaluations.removeAt(index);
  }

  sendEvaluatios() {
    if (this.evaluationForm.valid) this.onApprovalRuleChange.emit(this.evaluationForm.value);
  }

  reset() {
    this.evaluationForm.reset();
    this.createForm(this.approvalRule);
  }



}
