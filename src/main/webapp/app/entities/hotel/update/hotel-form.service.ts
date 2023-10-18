import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IHotel, NewHotel } from '../hotel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHotel for edit and NewHotelFormGroupInput for create.
 */
type HotelFormGroupInput = IHotel | PartialWithRequiredKeyOf<NewHotel>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IHotel | NewHotel> = Omit<T, 'date' | 'lastModified' | 'freeField7' | 'freeField8'> & {
  date?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

type HotelFormRawValue = FormValueOf<IHotel>;

type NewHotelFormRawValue = FormValueOf<NewHotel>;

type HotelFormDefaults = Pick<NewHotel, 'id' | 'date' | 'lastModified' | 'freeField7' | 'freeField8' | 'freeField9' | 'freeField10'>;

type HotelFormGroupContent = {
  id: FormControl<HotelFormRawValue['id'] | NewHotel['id']>;
  date: FormControl<HotelFormRawValue['date']>;
  name: FormControl<HotelFormRawValue['name']>;
  description: FormControl<HotelFormRawValue['description']>;
  location: FormControl<HotelFormRawValue['location']>;
  rent: FormControl<HotelFormRawValue['rent']>;
  emailId: FormControl<HotelFormRawValue['emailId']>;
  contactPerson: FormControl<HotelFormRawValue['contactPerson']>;
  status: FormControl<HotelFormRawValue['status']>;
  lastModified: FormControl<HotelFormRawValue['lastModified']>;
  lastModifiedBy: FormControl<HotelFormRawValue['lastModifiedBy']>;
  freeField1: FormControl<HotelFormRawValue['freeField1']>;
  freeField2: FormControl<HotelFormRawValue['freeField2']>;
  freeField5: FormControl<HotelFormRawValue['freeField5']>;
  freeField6: FormControl<HotelFormRawValue['freeField6']>;
  freeField7: FormControl<HotelFormRawValue['freeField7']>;
  freeField8: FormControl<HotelFormRawValue['freeField8']>;
  freeField9: FormControl<HotelFormRawValue['freeField9']>;
  freeField10: FormControl<HotelFormRawValue['freeField10']>;
};

export type HotelFormGroup = FormGroup<HotelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HotelFormService {
  createHotelFormGroup(hotel: HotelFormGroupInput = { id: null }): HotelFormGroup {
    const hotelRawValue = this.convertHotelToHotelRawValue({
      ...this.getFormDefaults(),
      ...hotel,
    });
    return new FormGroup<HotelFormGroupContent>({
      id: new FormControl(
        { value: hotelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      date: new FormControl(hotelRawValue.date),
      name: new FormControl(hotelRawValue.name),
      description: new FormControl(hotelRawValue.description),
      location: new FormControl(hotelRawValue.location),
      rent: new FormControl(hotelRawValue.rent),
      emailId: new FormControl(hotelRawValue.emailId),
      contactPerson: new FormControl(hotelRawValue.contactPerson),
      status: new FormControl(hotelRawValue.status),
      lastModified: new FormControl(hotelRawValue.lastModified),
      lastModifiedBy: new FormControl(hotelRawValue.lastModifiedBy),
      freeField1: new FormControl(hotelRawValue.freeField1),
      freeField2: new FormControl(hotelRawValue.freeField2),
      freeField5: new FormControl(hotelRawValue.freeField5),
      freeField6: new FormControl(hotelRawValue.freeField6),
      freeField7: new FormControl(hotelRawValue.freeField7),
      freeField8: new FormControl(hotelRawValue.freeField8),
      freeField9: new FormControl(hotelRawValue.freeField9),
      freeField10: new FormControl(hotelRawValue.freeField10),
    });
  }

  getHotel(form: HotelFormGroup): IHotel | NewHotel {
    return this.convertHotelRawValueToHotel(form.getRawValue() as HotelFormRawValue | NewHotelFormRawValue);
  }

  resetForm(form: HotelFormGroup, hotel: HotelFormGroupInput): void {
    const hotelRawValue = this.convertHotelToHotelRawValue({ ...this.getFormDefaults(), ...hotel });
    form.reset(
      {
        ...hotelRawValue,
        id: { value: hotelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HotelFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
      lastModified: currentTime,
      freeField7: currentTime,
      freeField8: currentTime,
      freeField9: false,
      freeField10: false,
    };
  }

  private convertHotelRawValueToHotel(rawHotel: HotelFormRawValue | NewHotelFormRawValue): IHotel | NewHotel {
    return {
      ...rawHotel,
      date: dayjs(rawHotel.date, DATE_TIME_FORMAT),
      lastModified: dayjs(rawHotel.lastModified, DATE_TIME_FORMAT),
      freeField7: dayjs(rawHotel.freeField7, DATE_TIME_FORMAT),
      freeField8: dayjs(rawHotel.freeField8, DATE_TIME_FORMAT),
    };
  }

  private convertHotelToHotelRawValue(
    hotel: IHotel | (Partial<NewHotel> & HotelFormDefaults),
  ): HotelFormRawValue | PartialWithRequiredKeyOf<NewHotelFormRawValue> {
    return {
      ...hotel,
      date: hotel.date ? hotel.date.format(DATE_TIME_FORMAT) : undefined,
      lastModified: hotel.lastModified ? hotel.lastModified.format(DATE_TIME_FORMAT) : undefined,
      freeField7: hotel.freeField7 ? hotel.freeField7.format(DATE_TIME_FORMAT) : undefined,
      freeField8: hotel.freeField8 ? hotel.freeField8.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
