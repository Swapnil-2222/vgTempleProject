import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Gender } from 'app/entities/enumerations/gender.model';
import { IDelegate } from '../delegate.model';
import { DelegateService } from '../service/delegate.service';
import { DelegateFormService, DelegateFormGroup } from './delegate-form.service';

@Component({
  standalone: true,
  selector: 'jhi-delegate-update',
  templateUrl: './delegate-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DelegateUpdateComponent implements OnInit {
  isSaving = false;
  delegate: IDelegate | null = null;
  genderValues = Object.keys(Gender);

  editForm: DelegateFormGroup = this.delegateFormService.createDelegateFormGroup();

  constructor(
    protected delegateService: DelegateService,
    protected delegateFormService: DelegateFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ delegate }) => {
      this.delegate = delegate;
      if (delegate) {
        this.updateForm(delegate);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const delegate = this.delegateFormService.getDelegate(this.editForm);
    if (delegate.id !== null) {
      this.subscribeToSaveResponse(this.delegateService.update(delegate));
    } else {
      this.subscribeToSaveResponse(this.delegateService.create(delegate));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDelegate>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(delegate: IDelegate): void {
    this.delegate = delegate;
    this.delegateFormService.resetForm(this.editForm, delegate);
  }
}
