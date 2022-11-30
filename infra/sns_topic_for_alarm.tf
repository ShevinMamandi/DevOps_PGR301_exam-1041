resource "aws_cloudwatch_metric_alarm" "zerosum" {
  alarm_name                = "Carts-count_larger_than_five"
  namespace                 = "1041"
  metric_name               = "carts.value"

  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "3"
  period                    = "300"

  statistic                 = "Maximum"

  alarm_description         = "En CloudWatch Alarm som løses ut dersom antall handlekurver over tre repeternde perioder,på fem minutter, overstiger verdien 5 "
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.user_updates.arn]
}

resource "aws_sns_topic" "user_updates" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.user_updates.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}