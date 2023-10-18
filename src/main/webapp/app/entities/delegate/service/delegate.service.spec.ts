import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDelegate } from '../delegate.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../delegate.test-samples';

import { DelegateService, RestDelegate } from './delegate.service';

const requireRestSample: RestDelegate = {
  ...sampleWithRequiredData,
  dateOfBirth: sampleWithRequiredData.dateOfBirth?.format(DATE_FORMAT),
  departureDate: sampleWithRequiredData.departureDate?.toJSON(),
  arrivalDate: sampleWithRequiredData.arrivalDate?.toJSON(),
  lastModified: sampleWithRequiredData.lastModified?.toJSON(),
  freeField7: sampleWithRequiredData.freeField7?.toJSON(),
  freeField8: sampleWithRequiredData.freeField8?.toJSON(),
};

describe('Delegate Service', () => {
  let service: DelegateService;
  let httpMock: HttpTestingController;
  let expectedResult: IDelegate | IDelegate[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DelegateService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Delegate', () => {
      const delegate = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(delegate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Delegate', () => {
      const delegate = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(delegate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Delegate', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Delegate', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Delegate', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDelegateToCollectionIfMissing', () => {
      it('should add a Delegate to an empty array', () => {
        const delegate: IDelegate = sampleWithRequiredData;
        expectedResult = service.addDelegateToCollectionIfMissing([], delegate);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(delegate);
      });

      it('should not add a Delegate to an array that contains it', () => {
        const delegate: IDelegate = sampleWithRequiredData;
        const delegateCollection: IDelegate[] = [
          {
            ...delegate,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDelegateToCollectionIfMissing(delegateCollection, delegate);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Delegate to an array that doesn't contain it", () => {
        const delegate: IDelegate = sampleWithRequiredData;
        const delegateCollection: IDelegate[] = [sampleWithPartialData];
        expectedResult = service.addDelegateToCollectionIfMissing(delegateCollection, delegate);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(delegate);
      });

      it('should add only unique Delegate to an array', () => {
        const delegateArray: IDelegate[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const delegateCollection: IDelegate[] = [sampleWithRequiredData];
        expectedResult = service.addDelegateToCollectionIfMissing(delegateCollection, ...delegateArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const delegate: IDelegate = sampleWithRequiredData;
        const delegate2: IDelegate = sampleWithPartialData;
        expectedResult = service.addDelegateToCollectionIfMissing([], delegate, delegate2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(delegate);
        expect(expectedResult).toContain(delegate2);
      });

      it('should accept null and undefined values', () => {
        const delegate: IDelegate = sampleWithRequiredData;
        expectedResult = service.addDelegateToCollectionIfMissing([], null, delegate, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(delegate);
      });

      it('should return initial array if no Delegate is added', () => {
        const delegateCollection: IDelegate[] = [sampleWithRequiredData];
        expectedResult = service.addDelegateToCollectionIfMissing(delegateCollection, undefined, null);
        expect(expectedResult).toEqual(delegateCollection);
      });
    });

    describe('compareDelegate', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDelegate(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareDelegate(entity1, entity2);
        const compareResult2 = service.compareDelegate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareDelegate(entity1, entity2);
        const compareResult2 = service.compareDelegate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareDelegate(entity1, entity2);
        const compareResult2 = service.compareDelegate(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
