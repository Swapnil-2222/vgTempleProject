import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../delegate.test-samples';

import { DelegateFormService } from './delegate-form.service';

describe('Delegate Form Service', () => {
  let service: DelegateFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DelegateFormService);
  });

  describe('Service methods', () => {
    describe('createDelegateFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDelegateFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            fullName: expect.any(Object),
            gender: expect.any(Object),
            nationality: expect.any(Object),
            countryOfResidence: expect.any(Object),
            dateOfBirth: expect.any(Object),
            citizenship: expect.any(Object),
            mobileNo: expect.any(Object),
            emailId: expect.any(Object),
            emergencyContactName: expect.any(Object),
            emergencyContactRelationship: expect.any(Object),
            emergencyContactNo: expect.any(Object),
            countryCodeOne: expect.any(Object),
            countryCodeTwo: expect.any(Object),
            nationalityOne: expect.any(Object),
            countryOfBirth: expect.any(Object),
            cityOfResidence: expect.any(Object),
            stateOfResidence: expect.any(Object),
            districtOfResidence: expect.any(Object),
            departureDate: expect.any(Object),
            departurePlace: expect.any(Object),
            arrivalDate: expect.any(Object),
            arrivalPlace: expect.any(Object),
            status: expect.any(Object),
            lastModified: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            isTaxReceipt: expect.any(Object),
            profilePhotoContentType: expect.any(Object),
            passportImageContentType: expect.any(Object),
            conferenceName: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            freeField4: expect.any(Object),
            freeField5: expect.any(Object),
            freeField6: expect.any(Object),
            freeField7: expect.any(Object),
            freeField8: expect.any(Object),
            freeField9: expect.any(Object),
            freeField10: expect.any(Object),
          }),
        );
      });

      it('passing IDelegate should create a new form with FormGroup', () => {
        const formGroup = service.createDelegateFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            fullName: expect.any(Object),
            gender: expect.any(Object),
            nationality: expect.any(Object),
            countryOfResidence: expect.any(Object),
            dateOfBirth: expect.any(Object),
            citizenship: expect.any(Object),
            mobileNo: expect.any(Object),
            emailId: expect.any(Object),
            emergencyContactName: expect.any(Object),
            emergencyContactRelationship: expect.any(Object),
            emergencyContactNo: expect.any(Object),
            countryCodeOne: expect.any(Object),
            countryCodeTwo: expect.any(Object),
            nationalityOne: expect.any(Object),
            countryOfBirth: expect.any(Object),
            cityOfResidence: expect.any(Object),
            stateOfResidence: expect.any(Object),
            districtOfResidence: expect.any(Object),
            departureDate: expect.any(Object),
            departurePlace: expect.any(Object),
            arrivalDate: expect.any(Object),
            arrivalPlace: expect.any(Object),
            status: expect.any(Object),
            lastModified: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            isTaxReceipt: expect.any(Object),
            profilePhotoContentType: expect.any(Object),
            passportImageContentType: expect.any(Object),
            conferenceName: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            freeField4: expect.any(Object),
            freeField5: expect.any(Object),
            freeField6: expect.any(Object),
            freeField7: expect.any(Object),
            freeField8: expect.any(Object),
            freeField9: expect.any(Object),
            freeField10: expect.any(Object),
          }),
        );
      });
    });

    describe('getDelegate', () => {
      it('should return NewDelegate for default Delegate initial value', () => {
        const formGroup = service.createDelegateFormGroup(sampleWithNewData);

        const delegate = service.getDelegate(formGroup) as any;

        expect(delegate).toMatchObject(sampleWithNewData);
      });

      it('should return NewDelegate for empty Delegate initial value', () => {
        const formGroup = service.createDelegateFormGroup();

        const delegate = service.getDelegate(formGroup) as any;

        expect(delegate).toMatchObject({});
      });

      it('should return IDelegate', () => {
        const formGroup = service.createDelegateFormGroup(sampleWithRequiredData);

        const delegate = service.getDelegate(formGroup) as any;

        expect(delegate).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDelegate should not enable id FormControl', () => {
        const formGroup = service.createDelegateFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDelegate should disable id FormControl', () => {
        const formGroup = service.createDelegateFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
