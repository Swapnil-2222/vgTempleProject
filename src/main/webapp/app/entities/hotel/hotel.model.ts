import dayjs from 'dayjs/esm';

export interface IHotel {
  id: number;
  date?: dayjs.Dayjs | null;
  name?: string | null;
  description?: string | null;
  location?: string | null;
  rent?: number | null;
  emailId?: string | null;
  contactPerson?: string | null;
  status?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField5?: number | null;
  freeField6?: number | null;
  freeField7?: dayjs.Dayjs | null;
  freeField8?: dayjs.Dayjs | null;
  freeField9?: boolean | null;
  freeField10?: boolean | null;
}

export type NewHotel = Omit<IHotel, 'id'> & { id: null };
