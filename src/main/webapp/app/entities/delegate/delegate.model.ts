import dayjs from 'dayjs/esm';
import { Gender } from 'app/entities/enumerations/gender.model';

export interface IDelegate {
  id: number;
  title?: string | null;
  fullName?: string | null;
  gender?: keyof typeof Gender | null;
  nationality?: string | null;
  countryOfResidence?: string | null;
  dateOfBirth?: dayjs.Dayjs | null;
  citizenship?: string | null;
  mobileNo?: number | null;
  emailId?: string | null;
  emergencyContactName?: string | null;
  emergencyContactRelationship?: string | null;
  emergencyContactNo?: number | null;
  countryCodeOne?: string | null;
  countryCodeTwo?: string | null;
  nationalityOne?: string | null;
  countryOfBirth?: string | null;
  cityOfResidence?: string | null;
  stateOfResidence?: string | null;
  districtOfResidence?: string | null;
  departureDate?: dayjs.Dayjs | null;
  departurePlace?: string | null;
  arrivalDate?: dayjs.Dayjs | null;
  arrivalPlace?: string | null;
  status?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  isTaxReceipt?: boolean | null;
  profilePhotoContentType?: string | null;
  passportImageContentType?: string | null;
  conferenceName?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: number | null;
  freeField6?: number | null;
  freeField7?: dayjs.Dayjs | null;
  freeField8?: dayjs.Dayjs | null;
  freeField9?: boolean | null;
  freeField10?: boolean | null;
}

export type NewDelegate = Omit<IDelegate, 'id'> & { id: null };
