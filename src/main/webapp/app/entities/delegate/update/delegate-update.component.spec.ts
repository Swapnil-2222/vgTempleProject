import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DelegateService } from '../service/delegate.service';
import { IDelegate } from '../delegate.model';
import { DelegateFormService } from './delegate-form.service';

import { DelegateUpdateComponent } from './delegate-update.component';

describe('Delegate Management Update Component', () => {
  let comp: DelegateUpdateComponent;
  let fixture: ComponentFixture<DelegateUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let delegateFormService: DelegateFormService;
  let delegateService: DelegateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DelegateUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DelegateUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DelegateUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    delegateFormService = TestBed.inject(DelegateFormService);
    delegateService = TestBed.inject(DelegateService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const delegate: IDelegate = { id: 456 };

      activatedRoute.data = of({ delegate });
      comp.ngOnInit();

      expect(comp.delegate).toEqual(delegate);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDelegate>>();
      const delegate = { id: 123 };
      jest.spyOn(delegateFormService, 'getDelegate').mockReturnValue(delegate);
      jest.spyOn(delegateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ delegate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: delegate }));
      saveSubject.complete();

      // THEN
      expect(delegateFormService.getDelegate).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(delegateService.update).toHaveBeenCalledWith(expect.objectContaining(delegate));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDelegate>>();
      const delegate = { id: 123 };
      jest.spyOn(delegateFormService, 'getDelegate').mockReturnValue({ id: null });
      jest.spyOn(delegateService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ delegate: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: delegate }));
      saveSubject.complete();

      // THEN
      expect(delegateFormService.getDelegate).toHaveBeenCalled();
      expect(delegateService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDelegate>>();
      const delegate = { id: 123 };
      jest.spyOn(delegateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ delegate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(delegateService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
