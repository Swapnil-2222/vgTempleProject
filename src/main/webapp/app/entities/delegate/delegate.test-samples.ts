import dayjs from 'dayjs/esm';

import { IDelegate, NewDelegate } from './delegate.model';

export const sampleWithRequiredData: IDelegate = {
  id: 7126,
};

export const sampleWithPartialData: IDelegate = {
  id: 32710,
  title: 'next normalisation',
  fullName: 'seldom chili',
  gender: 'FEMALE',
  countryOfResidence: 'fan',
  citizenship: 'beside hardship',
  mobileNo: 6810,
  emailId: 'until brr impeccable',
  emergencyContactRelationship: 'powder playfully',
  countryCodeOne: 'stretch martyr wasteful',
  countryCodeTwo: 'muddy persimmon slow',
  districtOfResidence: 'frugal',
  arrivalDate: dayjs('2023-10-18T01:28'),
  arrivalPlace: 'yippee',
  status: 'captain klap surprisingly',
  lastModifiedBy: 'including because er',
  isTaxReceipt: false,
  passportImageContentType: 'willfully whoever',
  conferenceName: 'calcify blah yuck',
  freeField2: 'phew for',
  freeField3: 'indeed upon supposing',
  freeField4: 'lymphocyte ethical blah',
  freeField5: 8600,
  freeField6: 21824,
  freeField8: dayjs('2023-10-18T16:52'),
  freeField10: false,
};

export const sampleWithFullData: IDelegate = {
  id: 25228,
  title: 'past drizzle',
  fullName: 'graceful knavishly',
  gender: 'FEMALE',
  nationality: 'urgently ballpark',
  countryOfResidence: 'vastly',
  dateOfBirth: dayjs('2023-10-18'),
  citizenship: 'boolean vice certificate',
  mobileNo: 13589,
  emailId: 'colorfully brightly',
  emergencyContactName: 'that midst',
  emergencyContactRelationship: 'past recent woot',
  emergencyContactNo: 15715,
  countryCodeOne: 'yowza',
  countryCodeTwo: 'please keenly than',
  nationalityOne: 'if',
  countryOfBirth: 'where judgementally cultured',
  cityOfResidence: 'oily',
  stateOfResidence: 'phew for once',
  districtOfResidence: 'about whereas',
  departureDate: dayjs('2023-10-18T04:38'),
  departurePlace: 'quixotic palatable',
  arrivalDate: dayjs('2023-10-18T06:56'),
  arrivalPlace: 'intoxicate drat',
  status: 'childbirth sticky unknown',
  lastModified: dayjs('2023-10-18T12:52'),
  lastModifiedBy: 'chem',
  isTaxReceipt: false,
  profilePhotoContentType: 'however er',
  passportImageContentType: 'brother adolescent',
  conferenceName: 'um any',
  freeField1: 'despite salty ack',
  freeField2: 'decisive yowza why',
  freeField3: 'atop hm order',
  freeField4: 'softly breathalyze',
  freeField5: 6714,
  freeField6: 27099,
  freeField7: dayjs('2023-10-17T20:14'),
  freeField8: dayjs('2023-10-18T04:46'),
  freeField9: false,
  freeField10: false,
};

export const sampleWithNewData: NewDelegate = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
