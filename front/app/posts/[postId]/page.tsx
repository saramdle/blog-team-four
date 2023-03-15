import Container from "@/app/components/Container";
import GoogleMapAPI from "@/app/components/GoogleMapAPI";
import { DUMMY_POSTS } from "@/constants/dummy";
import Image from "next/image";

export default async function Page({
  params,
}: {
  params: {
    postId: string;
  };
}) {
  const postId = params.postId;
  const POST = DUMMY_POSTS.find((post) => post.id === postId);
  console.log(process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY);
  return (
    <Container>
      <h1>{POST?.title}</h1>
      <Image
        alt={POST?.title || "food image"}
        src={POST?.image || ""}
        width={300}
        height={300}
      />
      <p>{POST?.contents}</p>
      <p>{POST?.author}</p>
      <p>{POST?.createdAt}</p>
      <GoogleMapAPI />
      <div>
        <h2>코멘트</h2>
        <ul>
          {POST?.comments.map((comment, index) => (
            <li key={index}>{comment}</li>
          ))}
        </ul>
      </div>
    </Container>
  );
}
