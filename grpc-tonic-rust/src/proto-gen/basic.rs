#[allow(clippy::derive_partial_eq_without_eq)]
#[derive(Clone, PartialEq, ::prost::Message)]
pub struct BaseResponse {
    #[prost(string, tag = "1")]
    pub message: ::prost::alloc::string::String,
    #[prost(int32, tag = "2")]
    pub code: i32,
}
