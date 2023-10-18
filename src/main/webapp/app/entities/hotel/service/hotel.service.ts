import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHotel, NewHotel } from '../hotel.model';

export type PartialUpdateHotel = Partial<IHotel> & Pick<IHotel, 'id'>;

type RestOf<T extends IHotel | NewHotel> = Omit<T, 'date' | 'lastModified' | 'freeField7' | 'freeField8'> & {
  date?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

export type RestHotel = RestOf<IHotel>;

export type NewRestHotel = RestOf<NewHotel>;

export type PartialUpdateRestHotel = RestOf<PartialUpdateHotel>;

export type EntityResponseType = HttpResponse<IHotel>;
export type EntityArrayResponseType = HttpResponse<IHotel[]>;

@Injectable({ providedIn: 'root' })
export class HotelService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hotels');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(hotel: NewHotel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hotel);
    return this.http.post<RestHotel>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(hotel: IHotel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hotel);
    return this.http
      .put<RestHotel>(`${this.resourceUrl}/${this.getHotelIdentifier(hotel)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(hotel: PartialUpdateHotel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hotel);
    return this.http
      .patch<RestHotel>(`${this.resourceUrl}/${this.getHotelIdentifier(hotel)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestHotel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestHotel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHotelIdentifier(hotel: Pick<IHotel, 'id'>): number {
    return hotel.id;
  }

  compareHotel(o1: Pick<IHotel, 'id'> | null, o2: Pick<IHotel, 'id'> | null): boolean {
    return o1 && o2 ? this.getHotelIdentifier(o1) === this.getHotelIdentifier(o2) : o1 === o2;
  }

  addHotelToCollectionIfMissing<Type extends Pick<IHotel, 'id'>>(
    hotelCollection: Type[],
    ...hotelsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hotels: Type[] = hotelsToCheck.filter(isPresent);
    if (hotels.length > 0) {
      const hotelCollectionIdentifiers = hotelCollection.map(hotelItem => this.getHotelIdentifier(hotelItem)!);
      const hotelsToAdd = hotels.filter(hotelItem => {
        const hotelIdentifier = this.getHotelIdentifier(hotelItem);
        if (hotelCollectionIdentifiers.includes(hotelIdentifier)) {
          return false;
        }
        hotelCollectionIdentifiers.push(hotelIdentifier);
        return true;
      });
      return [...hotelsToAdd, ...hotelCollection];
    }
    return hotelCollection;
  }

  protected convertDateFromClient<T extends IHotel | NewHotel | PartialUpdateHotel>(hotel: T): RestOf<T> {
    return {
      ...hotel,
      date: hotel.date?.toJSON() ?? null,
      lastModified: hotel.lastModified?.toJSON() ?? null,
      freeField7: hotel.freeField7?.toJSON() ?? null,
      freeField8: hotel.freeField8?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restHotel: RestHotel): IHotel {
    return {
      ...restHotel,
      date: restHotel.date ? dayjs(restHotel.date) : undefined,
      lastModified: restHotel.lastModified ? dayjs(restHotel.lastModified) : undefined,
      freeField7: restHotel.freeField7 ? dayjs(restHotel.freeField7) : undefined,
      freeField8: restHotel.freeField8 ? dayjs(restHotel.freeField8) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestHotel>): HttpResponse<IHotel> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestHotel[]>): HttpResponse<IHotel[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
