terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }
  backend "s3" {
    bucket = "analytics-1041"
    key    = "1041/terraform.state"
    region = "eu-west-1"
  }
}