package br.com.douglasffilho.CarServices.rest.api.publicEndpoints;

public interface PublicCarMakerRestApiV1Endpoints {

	static final String PUBLIC_API_V1 = "/public/api/v1";

	static final String PUBLIC_API_V1_CAR_MAKERS_ROOT_ENDPOINT = PUBLIC_API_V1 + "/car-makers";

	static final String PUBLIC_API_V1_LIST_CAR_MAKERS_ENDPOINT = "/find";

	static final String PUBLIC_API_V1_FIND_CAR_MAKER_BY_NAME_ENDPOINT = "/find/{name}";

	static final String PUBLIC_API_V1_REGISTER_CAR_MAKER_ENDPOINT = "/register";

	static final String PUBLIC_API_V1_UPDATE_CAR_MAKER_ENDPOINT = "/update/{carMakerId}";

}
