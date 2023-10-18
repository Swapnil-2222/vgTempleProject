import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDelegate, NewDelegate } from '../delegate.model';

export type PartialUpdateDelegate = Partial<IDelegate> & Pick<IDelegate, 'id'>;

type RestOf<T extends IDelegate | NewDelegate> = Omit<
  T,
  'dateOfBirth' | 'departureDate' | 'arrivalDate' | 'lastModified' | 'freeField7' | 'freeField8'
> & {
  dateOfBirth?: string | null;
  departureDate?: string | null;
  arrivalDate?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

export type RestDelegate = RestOf<IDelegate>;

export type NewRestDelegate = RestOf<NewDelegate>;

export type PartialUpdateRestDelegate = RestOf<PartialUpdateDelegate>;

export type EntityResponseType = HttpResponse<IDelegate>;
export type EntityArrayResponseType = HttpResponse<IDelegate[]>;

@Injectable({ providedIn: 'root' })
export class DelegateService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/delegates');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(delegate: NewDelegate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(delegate);
    return this.http
      .post<RestDelegate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(delegate: IDelegate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(delegate);
    return this.http
      .put<RestDelegate>(`${this.resourceUrl}/${this.getDelegateIdentifier(delegate)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(delegate: PartialUpdateDelegate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(delegate);
    return this.http
      .patch<RestDelegate>(`${this.resourceUrl}/${this.getDelegateIdentifier(delegate)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestDelegate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDelegate[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDelegateIdentifier(delegate: Pick<IDelegate, 'id'>): number {
    return delegate.id;
  }

  compareDelegate(o1: Pick<IDelegate, 'id'> | null, o2: Pick<IDelegate, 'id'> | null): boolean {
    return o1 && o2 ? this.getDelegateIdentifier(o1) === this.getDelegateIdentifier(o2) : o1 === o2;
  }

  addDelegateToCollectionIfMissing<Type extends Pick<IDelegate, 'id'>>(
    delegateCollection: Type[],
    ...delegatesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const delegates: Type[] = delegatesToCheck.filter(isPresent);
    if (delegates.length > 0) {
      const delegateCollectionIdentifiers = delegateCollection.map(delegateItem => this.getDelegateIdentifier(delegateItem)!);
      const delegatesToAdd = delegates.filter(delegateItem => {
        const delegateIdentifier = this.getDelegateIdentifier(delegateItem);
        if (delegateCollectionIdentifiers.includes(delegateIdentifier)) {
          return false;
        }
        delegateCollectionIdentifiers.push(delegateIdentifier);
        return true;
      });
      return [...delegatesToAdd, ...delegateCollection];
    }
    return delegateCollection;
  }

  protected convertDateFromClient<T extends IDelegate | NewDelegate | PartialUpdateDelegate>(delegate: T): RestOf<T> {
    return {
      ...delegate,
      dateOfBirth: delegate.dateOfBirth?.format(DATE_FORMAT) ?? null,
      departureDate: delegate.departureDate?.toJSON() ?? null,
      arrivalDate: delegate.arrivalDate?.toJSON() ?? null,
      lastModified: delegate.lastModified?.toJSON() ?? null,
      freeField7: delegate.freeField7?.toJSON() ?? null,
      freeField8: delegate.freeField8?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDelegate: RestDelegate): IDelegate {
    return {
      ...restDelegate,
      dateOfBirth: restDelegate.dateOfBirth ? dayjs(restDelegate.dateOfBirth) : undefined,
      departureDate: restDelegate.departureDate ? dayjs(restDelegate.departureDate) : undefined,
      arrivalDate: restDelegate.arrivalDate ? dayjs(restDelegate.arrivalDate) : undefined,
      lastModified: restDelegate.lastModified ? dayjs(restDelegate.lastModified) : undefined,
      freeField7: restDelegate.freeField7 ? dayjs(restDelegate.freeField7) : undefined,
      freeField8: restDelegate.freeField8 ? dayjs(restDelegate.freeField8) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDelegate>): HttpResponse<IDelegate> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDelegate[]>): HttpResponse<IDelegate[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
