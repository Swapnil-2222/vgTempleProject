import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IShop, NewShop } from '../shop.model';

export type PartialUpdateShop = Partial<IShop> & Pick<IShop, 'id'>;

type RestOf<T extends IShop | NewShop> = Omit<T, 'date' | 'lastModified' | 'freeField7' | 'freeField8'> & {
  date?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

export type RestShop = RestOf<IShop>;

export type NewRestShop = RestOf<NewShop>;

export type PartialUpdateRestShop = RestOf<PartialUpdateShop>;

export type EntityResponseType = HttpResponse<IShop>;
export type EntityArrayResponseType = HttpResponse<IShop[]>;

@Injectable({ providedIn: 'root' })
export class ShopService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/shops');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(shop: NewShop): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shop);
    return this.http.post<RestShop>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(shop: IShop): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shop);
    return this.http
      .put<RestShop>(`${this.resourceUrl}/${this.getShopIdentifier(shop)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(shop: PartialUpdateShop): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shop);
    return this.http
      .patch<RestShop>(`${this.resourceUrl}/${this.getShopIdentifier(shop)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestShop>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestShop[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getShopIdentifier(shop: Pick<IShop, 'id'>): number {
    return shop.id;
  }

  compareShop(o1: Pick<IShop, 'id'> | null, o2: Pick<IShop, 'id'> | null): boolean {
    return o1 && o2 ? this.getShopIdentifier(o1) === this.getShopIdentifier(o2) : o1 === o2;
  }

  addShopToCollectionIfMissing<Type extends Pick<IShop, 'id'>>(
    shopCollection: Type[],
    ...shopsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const shops: Type[] = shopsToCheck.filter(isPresent);
    if (shops.length > 0) {
      const shopCollectionIdentifiers = shopCollection.map(shopItem => this.getShopIdentifier(shopItem)!);
      const shopsToAdd = shops.filter(shopItem => {
        const shopIdentifier = this.getShopIdentifier(shopItem);
        if (shopCollectionIdentifiers.includes(shopIdentifier)) {
          return false;
        }
        shopCollectionIdentifiers.push(shopIdentifier);
        return true;
      });
      return [...shopsToAdd, ...shopCollection];
    }
    return shopCollection;
  }

  protected convertDateFromClient<T extends IShop | NewShop | PartialUpdateShop>(shop: T): RestOf<T> {
    return {
      ...shop,
      date: shop.date?.toJSON() ?? null,
      lastModified: shop.lastModified?.toJSON() ?? null,
      freeField7: shop.freeField7?.toJSON() ?? null,
      freeField8: shop.freeField8?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restShop: RestShop): IShop {
    return {
      ...restShop,
      date: restShop.date ? dayjs(restShop.date) : undefined,
      lastModified: restShop.lastModified ? dayjs(restShop.lastModified) : undefined,
      freeField7: restShop.freeField7 ? dayjs(restShop.freeField7) : undefined,
      freeField8: restShop.freeField8 ? dayjs(restShop.freeField8) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestShop>): HttpResponse<IShop> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestShop[]>): HttpResponse<IShop[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
