import { NextRequest } from "next/server";

export async function GET(
  req: NextRequest,
  { params }: { params: { id: string }}) {
  return Response.json({
    id: params.id,
    title: `doc${params.id}`,
    content: `hello world${params.id}`
  })
}